package Aufgabe1;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Tutorial {
    public static void main(String args[]) {
        Graph graph = new SingleGraph("Tutorial 1");
        //graph.setStrict(false);
        //graph.setAutoCreate(false);
        graph.addNode("A" );
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        System.setProperty("org.graphstream.ui", "swing");
        graph.display();
    }
}
