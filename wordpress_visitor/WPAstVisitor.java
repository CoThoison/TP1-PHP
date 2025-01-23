package code_to_analyze;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WPAstVisitor {

    class ASTNode {
        private final String id;
        private Map<String, Object> attributes;

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

        @Override
        public String toString() {
            return "ASTNode{id='" + id + "', attributes=" + attributes + '}';
        }
    }

    private Map<String, WPAstVisitor.ASTNode> parseJsonToNodes(JSONArray jsonArray) {
        Map<String, WPAstVisitor.ASTNode> nodeMap = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            Object element = jsonArray.get(i);
            if (element instanceof JSONArray) {
                JSONArray array = (JSONArray) element;

                if (array.length() == 3) {
                    String attribute = array.getString(0);
                    String id = Integer.toString(array.getInt(1));
                    Object value = array.get(2);

                    WPAstVisitor.ASTNode node = nodeMap.getOrDefault(id, new WPAstVisitor.ASTNode(id));
                    node.addAttribute(attribute, value);
                    nodeMap.put(id, node);
                }
            }
        }

        return nodeMap;
    }

    Map<String,List<Integer>> linesForFile = new HashMap<>();

    public static void main(String[] args) {
        try {
            String pathToAst = args[0];
            String pathToSources = args[1];
            WPAstVisitor visitor = new WPAstVisitor();
            visitor.analyzeFiles(pathToAst);
            String[] inter = pathToAst.split("/");
            pathToAst = inter[inter.length - 1];
            BufferedReader reader;
            inter = pathToSources.split("/");
            pathToSources = inter[inter.length - 1];
            for (Map.Entry<String,List<Integer>> fileInformation : visitor.linesForFile.entrySet()) {
                String path = fileInformation.getKey();
                String[] tabPath = path.split("/");
                path = "";
                int i = 0;
                String piece = tabPath[i];
                while (piece != tabPath[tabPath.length - 1]) {
                    if (piece.equals(pathToAst)) {
                        piece = pathToSources;
                    }
                    path += piece + "/";
                    i++;
                    piece = tabPath[i];
                }
                inter = piece.split("\\.");
                path+=inter[0] +"."+ inter[1];
                List<Integer> lines = fileInformation.getValue();
                reader = new BufferedReader(new FileReader(path));
                String line = reader.readLine();
                i = 1;
                System.out.println(fileInformation.getKey());
                while (line != null) {
                    if (lines.contains(i)) {
                        System.out.println(line);
                    }
                    line = reader.readLine();
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error occured during collecting sql patterns");
            e.printStackTrace();
        }
    }

    private void analyzeFiles(String pathAst) {
        try {
            List<Path> jsonFiles = listJsonFiles(pathAst);
            jsonFiles.forEach(System.out::println);

            for (Path jsonFile : jsonFiles) {
                String content = Files.readString(jsonFile);
                JSONArray jsonArray = new JSONArray(content);
                WPAstVisitor visitor = new WPAstVisitor();
                Map<String,ASTNode> nodes = visitor.parseJsonToNodes(jsonArray);
                ArrayList<Integer> detect = visitor.detectSQLCalls(nodes);
                if (!detect.isEmpty()) {
                    System.out.println("In file " + jsonFile.toString());
                    this.linesForFile.put(jsonFile.toString(), detect);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }

    private List<Path> listJsonFiles(String rootDirectory) throws IOException {
        List<Path> jsonFiles = new ArrayList<>();
        Files.walk(Paths.get(rootDirectory))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".json"))
                .forEach(jsonFiles::add);
        return jsonFiles;
    }

    private ArrayList<Integer> detectSQLCalls(Map<String, WPAstVisitor.ASTNode> nodes) {
        ArrayList<Integer> linesQueries = new ArrayList<>();
        for (WPAstVisitor.ASTNode node : nodes.values()) {
            String type = (String) node.getAttributes().get("type");
            String image = (String) node.getAttributes().get("image");

            if ("FunctionCall".equals(type) && ("mysql_query".equals(image) || "mysqli_query".equals(image))) {
                System.out.println("Detected SQL function call in line: " + node.getAttributes().get("line_begin"));
                linesQueries.add((Integer) node.getAttributes().get("line_begin"));
            }

            if ("MethodCall".equals(type) && ("execute".equals(image) || "exec".equals(image))) {
                System.out.println("Detected SQL method call in line: " + node.getAttributes().get("line_begin"));
                linesQueries.add((Integer) node.getAttributes().get("line_begin"));
            }
        }
        return linesQueries;
    }
}
