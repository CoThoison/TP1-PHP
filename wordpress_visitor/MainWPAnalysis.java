package code_to_analyze;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MainWPAnalysis {

    /**
     * Main method to start AST files analysis.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected template: java MainWPAnalysis path/to/ast/root/directory");
            System.exit(1);
        }
        String pathToAst = args[0];
        try {
            List<Path> jsonFiles = listJsonFiles(pathToAst);
            for (Path jsonFile : jsonFiles) {
                String content = Files.readString(jsonFile);
                JSONArray jsonArray = new JSONArray(content);

                ASTTransformer transformer = new ASTTransformer();
                Map<String, ASTNode> nodes = transformer.parseJsonToNodes(jsonArray);

                WPAstVisitor visitor = new WPAstVisitor();
                for (ASTNode node : nodes.values()) {
                    node.accept(visitor);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }

    /**
     * List all JSON files in the given directory.
     * @param rootDirectory the root directory to search for JSON files
     */
    private static List<Path> listJsonFiles(String rootDirectory) throws IOException {
        List<Path> jsonFiles = new ArrayList<>();
        try (var paths = Files.walk(Paths.get(rootDirectory))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(jsonFiles::add);
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
        return jsonFiles;
    }
}
/**
 * ASTVisitor interface to visit AST nodes.
 */
interface ASTVisitor {
    void visit(ASTNode node);
}

/**
 * ASTNode class to represent AST nodes with attributes.
 */
class ASTNode {
    private final String id;
    private final Map<String, Object> attributes;
    public ASTNode(String id) {
        this.id = id;
        this.attributes = new HashMap<>();
    }
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    public void addAttribute(String key, Object value) {
        attributes.put(key, value);
    }
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
    @Override
    public String toString() {
        return "ASTNode{id='" + id + "', attributes=" + attributes + '}';
    }
}
/**
 * ASTTransformer class to parse JSON array to AST nodes.
 */
class ASTTransformer {
    /**
     * Parse JSON array to AST nodes.
     * @param jsonArray the JSON array to parse
     * @return a map of AST nodes with their IDs as keys
     */
    public Map<String, ASTNode> parseJsonToNodes(JSONArray jsonArray) {
        Map<String, ASTNode> nodeMap = new HashMap<>();
        String filename = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            Object element = jsonArray.get(i);
            if (element instanceof JSONArray array) {
                if (array.length() == 3) {
                    String attribute = array.getString(0);
                    String id = Integer.toString(array.getInt(1));
                    Object value = array.get(2);
                    ASTNode node = nodeMap.getOrDefault(id, new ASTNode(id));
                    node.addAttribute(attribute, value);
                    if (!filename.isEmpty()) {
                        node.addAttribute("filename", filename);
                    }
                    nodeMap.put(id, node);
                } else if (array.length() == 2 && "filename".equals(array.getString(0))) {
                    filename = array.getString(1);
                }
            }
        }
        if (filename.isEmpty()) {
            throw new IllegalStateException("Filename is not specified in the JSON.");
        }
        return nodeMap;
    }
}

/**
 * WPAstVisitor class to visit AST nodes and detect SQL function calls.
 */
class WPAstVisitor implements ASTVisitor {

    private static final Set<String> SQL_FUNCTIONS = Set.of("mysql_query", "mysqli_query");
    private static final Set<String> SQL_METHODS = Set.of("execute", "exec", "query", "get_results", "get_row", "get_col", "get_var");

    /**
     * Visit the given AST node and detect SQL function calls.
     * @param node the AST node to visit
     */
    @Override
    public void visit(ASTNode node) {
        String type = (String) node.getAttributes().get("type");
        String image = (String) node.getAttributes().get("image");
        String fileName = (String) node.getAttributes().get("filename");

        if ("FunctionCall".equals(type) && image != null && SQL_FUNCTIONS.contains(image)) {
            System.out.println("Detected SQL function call " + image + " in line: " + node.getAttributes().get("line_begin") + " in file " + fileName);
        }

        if ("MethodCall".equals(type) && image != null && SQL_METHODS.contains(image)) {
            System.out.println("Detected SQL method call " + image +" in line: " + node.getAttributes().get("line_begin") + " in file " + fileName);
        }
    }
}