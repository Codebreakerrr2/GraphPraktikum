package Aufgabe1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Element;
import org.graphstream.graph.implementations.MultiGraph;

public class GraphLesen {
    /**
     * @author Usman Amini, Andre
     * Die Methode liest eine Graph-Datei und erzeugt aus der Datei ein Graph-Objekt.
     * @param fileName
     * @return Graph-Objekt/Null
     * */


    public static Graph readGraph(String fileName) {
        Pattern directedPattern =Pattern.compile("\\s*(?<nameNode1>\\w+)\\s*(->\\s*(?<nameNode2>\\w+)\\s*(?<edgeName>\\(\\w+\\))?\\s*(:\\s*(?<edgeGewicht>\\d+))?)?\\s*;");
        Pattern undirectedPattern = Pattern.compile("\\s*(?<nameNode1>\\w+)\\s*(--\\s*(?<nameNode2>\\w+)\\s*(?<edgeName>\\(\\w+\\))?\\s*(:\\s*(?<edgeGewicht>\\d+))?)?\\s*;");
        Graph graph = new MultiGraph(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line ;

            while ((line = br.readLine()) != null) {
                Matcher directedMatch=directedPattern.matcher(line);
                Matcher undirectedMatch=undirectedPattern.matcher(line);
                if(!(directedMatch.matches() || undirectedMatch.matches())) return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
   
        }


        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher directedMatch=directedPattern.matcher(line);
                Matcher undirectedMatch=undirectedPattern.matcher(line);
                if(directedMatch.matches() || undirectedMatch.matches()){

                    String nameNode1= directedMatch.group("nameNode1");
                    String nameNode2= directedMatch.group("nameNode2");
                    String edgeName= directedMatch.group("edgeName");
                    String edgeGewicht=directedMatch.group("edgeGewicht");

                    Node node1= graph.getNode(nameNode1);
                    if(node1==null) node1=graph.addNode(nameNode1);
                    if(nameNode2!=null){
                        Node node2= graph.getNode(nameNode2);
                        if(node2==null) node2= graph.addNode(nameNode2);
                        if(directedMatch.matches()){
                        if(edgeName==null) edgeName=nameNode1+"->"+nameNode2;
                        Edge edge= graph.addEdge(edgeName,node1,node2,true);
                        }
                        else{
                            if(edgeName==null) edgeName=nameNode1+"--"+nameNode2;
                            Edge edge= graph.addEdge(edgeName,node1,node2,false);
                        }
                        if(edgeGewicht!=null) graph.getEdge(edgeName).setAttribute("Gewicht",Double.parseDouble(edgeGewicht));

                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;


    }




    public static void main(String[] args) throws IOException {
        //"C:\\Users\\Usman\\Documents\\Java Files\\GraphPraktikum\\src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph01.gka"
    Graph graph= GraphLesen.readGraph("C:\\Users\\Usman\\Documents\\Java Files\\GraphPraktikum\\src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph01.gka");

    }
}
