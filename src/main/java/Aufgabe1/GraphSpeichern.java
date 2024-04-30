package Aufgabe1;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphSpeichern {
    /**
     * @author Usman Amini, Andre Demir.
     * Die Methode speichert einen Graphen in einer Datei.
     * @param graph
     * */
    public static void saveGraphToFile(Graph graph, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Edge edge : graph.edges().toList()) {
                String node1 = edge.getNode0().getId();
                String node2 = edge.getNode1().getId();
                String direction = edge.isDirected() ? "->" : "--";
                String edgeName = edge.hasAttribute("ui.label") ? edge.getAttribute("ui.label").toString() : "";
                //String edgeWeight = edge.hasAttribute("Gewicht") ? edge.getAttribute("Gewicht").toString() : "";
                if (!edgeName.equals("")){
                    writer.write(node1 + " " + direction + " " + node2 + " : " + edgeName + ";\n");
                }if (edgeName.equals("")){
                    writer.write(node1 + " " + direction + " " + node2 + ";\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Graph graph = GraphLesen.readGraph("C:\\Users\\andre\\Desktop\\GraphPraktikum\\src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph07.gka");
        saveGraphToFile(graph, "C:\\Users\\andre\\Desktop\\GraphPraktikum\\src\\main\\java\\Aufgabe1\\graphDateien\\graph.txt");
    }
}
