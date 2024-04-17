package Aufgabe1;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Tutorial {
    public static void main(String args[]) {
        Graph graph = new SingleGraph("Tutorial 1");
        graph.setStrict(false);
        graph.setAutoCreate( true );
        graph.addEdge( "AB", "A", "B" );
        graph.addEdge( "BC", "B", "C" );
        graph.addEdge( "CA", "C", "A" );
        System.setProperty("org.graphstream.ui", "swing");
        Node A = graph.getNode("A");
        Edge AB = graph.getEdge("AB");
        System.out.println(A.getDegree());
        graph.display();
    }
}
