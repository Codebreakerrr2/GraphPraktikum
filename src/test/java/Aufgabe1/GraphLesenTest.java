package Aufgabe1;

import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphLesenTest {

    @Test
    void readDirectedGraphWithoutWeight() {
        assertNotNull(GraphLesen.readGraph("C:\\Users\\andre\\Desktop\\GraphPraktikum\\src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph01.gka"));
    }

    @Test
    void readUndirectedGraphWithWeight(){
        assertNotNull(GraphLesen.readGraph("C:\\Users\\andre\\Desktop\\GraphPraktikum\\src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph04.gka"));
    }

    @Test
    void readDirectedGraphWithWeight(){
        assertNotNull(GraphLesen.readGraph("C:\\Users\\andre\\Desktop\\GraphPraktikum\\src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph05.gka"));
    }
}