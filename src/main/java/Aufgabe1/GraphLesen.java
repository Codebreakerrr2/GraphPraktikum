package Aufgabe1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

public class GraphLesen {

    public static Graph readGraph(String filename) throws IOException {
        Pattern directedPattern = Pattern.compile("\\s*(?<nameNode1>\\w)\\s*(->\\s*(?<nameNode2>\\w)\\s*(?<edgeName>\\(\\w\\))?\\s*(:\\s*(?<edgeGewicht>\\d))?)?\\s*;");
        Pattern undirectedPattern = Pattern.compile("\\s*(?<nameNode1>\\w)\\s*(\\s*--\\s*(?<nameNode2>\\w)\\s*(?<edgeName>\\(\\w\\))?\\s*(:\\s*(?<edgeGewicht>\\d))?)?\\s*;");
        Graph graph = new MultiGraph(filename);
        List<String> linesFromFile;

        if (Files.exists(Paths.get("Graph"))) {
            linesFromFile = Files.readAllLines(Paths.get(filename));
        }else {
            System.out.println("Datei nicht gefunden");
            return null;
        }
        try {
            for (String line : linesFromFile) {
                Matcher directedMatcher = directedPattern.matcher(line);
                Matcher undirectedMatcher = undirectedPattern.matcher(line);
                if (directedMatcher.matches()) {

                } else if (undirectedMatcher.matches()) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return graph;
    }

    public static void main(String[] args) throws IOException {
        String graphZeileNormaler = "A ->  B ;";
        String graphZeileNormalerMitDplPktUndGewicht = "A ->  B: 23 ;";
        String graphZeileNormalerMitKlammerUndGewicht = "A ->  B (23) ;";
        Pattern directedPattern = Pattern.compile("((\\w+)\\s*->\\s*(\\w+)\\s*)((:\\s*\\w+\\s*) | (\\((\\s*\\w+\\s*)\\)))*\\s*;");
        System.out.println(directedPattern.matcher(graphZeileNormaler).matches());
        System.out.println(directedPattern.matcher(graphZeileNormalerMitDplPktUndGewicht).matches());
        System.out.println(directedPattern.matcher(graphZeileNormalerMitKlammerUndGewicht).matches());
    }
}
