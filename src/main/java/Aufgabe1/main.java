package Aufgabe1;

import org.graphstream.graph.Graph;

import static Aufgabe1.GraphSpeichern.saveGraphToFile4;

public class main {
    public static void main(String[] args) {
        Graph graph = GraphLesen.readGraph("C:\\Users\\Usman\\Documents\\Java Files\\GraphPraktikum\\branchAndre\\src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph10.gka");
        saveGraphToFile4(graph, "C:\\Users\\Usman\\Documents\\Java Files\\GraphPraktikum\\branchAndre\\src\\main\\java\\Aufgabe1\\graphDateien\\graph.txt");
    }
}
