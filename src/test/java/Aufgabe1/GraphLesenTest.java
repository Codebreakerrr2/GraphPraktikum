package Aufgabe1;

import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class GraphLesenTest {

    @Test
    void readDirectedGraphWithoutWeightFile01(){
        assertNotNull(GraphLesen.readGraph("src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph01.gka"));
    }

    @Test
    void readUndirectedGraphWithWeightFile04(){
        assertNotNull(GraphLesen.readGraph("src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph04.gka"));
    }

    @Test
    void readDirectedGraphWithWeightFile05CorruptFile(){
        assertNull(GraphLesen.readGraph("src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph05.gka"));
    }
}