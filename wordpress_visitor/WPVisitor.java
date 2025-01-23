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

public class WPVisitor {

    Map<String,List<Integer>> linesForFile = new HashMap<>();

    public static void main(String[] args) {
        try {
            String pathToAst = args[0];
            String pathToSources = args[1];
            WPVisitor visitor = new WPVisitor();
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
            System.out.println("Found JSON files:");
            jsonFiles.forEach(System.out::println);

            for (Path jsonFile : jsonFiles) {
                System.out.println("\nReading file: " + jsonFile);
                observeJsonFile(jsonFile);
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

    private void observeJsonFile(Path jsonFile) throws RuntimeException {
        try {
            String content = Files.readString(jsonFile);
            JSONArray jsonArray = new JSONArray(content);

            System.out.println("Content of " + jsonFile + ":");
            ArrayList<Integer> linesQueries = new ArrayList<>();
            boolean gotFunctionCall = false;
            boolean gotMethodCall = false;
            boolean shouldGetLine = false;
            for (int i = 0; i < jsonArray.length(); i++) {
                Object element = jsonArray.get(i);

                if (element instanceof JSONArray) {
                    JSONArray myArray = (JSONArray) element;
                    if (myArray.length() == 3) {
                        if (myArray.get(0).equals("type") && myArray.get(2).equals("FunctionCall")) {
                            gotFunctionCall = true;
                        } else if (myArray.get(0).equals("type") && (myArray.get(2).equals("MethodCall"))) {
                            gotMethodCall = true;
                        } else if (gotFunctionCall && myArray.get(0).equals("image") && (myArray.get(2).equals("mysql_query") || myArray.get(2).equals("mysqli_query"))) {
                            shouldGetLine = true;
                            gotFunctionCall = false;
                        } else if (gotMethodCall && myArray.get(0).equals("image") && (myArray.get(2).equals("execute") || myArray.get(2).equals("exec"))) {
                            shouldGetLine = true;
                            gotMethodCall = false;
                        } else if (shouldGetLine && myArray.get(0).equals("line_begin")) {
                            linesQueries.add((Integer) myArray.get(2));
                            shouldGetLine = false;
                        } else if ((gotFunctionCall || gotMethodCall) && myArray.get(0).equals("image")) {
                            gotFunctionCall = false;
                            gotMethodCall = false;
                        }
                    }
                } else {
                    throw new RuntimeException("Error in the input json object");
                }
            }
            if (!linesQueries.isEmpty()) {
                this.linesForFile.put(jsonFile.toString(), linesQueries);
            }
        } catch (Exception e) {
            System.err.println("Error reading JSON file " + jsonFile + ": " + e.getMessage());
        }
    }
}
