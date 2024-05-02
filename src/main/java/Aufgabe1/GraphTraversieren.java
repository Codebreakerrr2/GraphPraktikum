package Aufgabe1;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.*;

public class GraphTraversieren {
    public static HashSet<Node> traverseGraph(Graph graph, String startNode) {
        Node node = graph.getNode(startNode);
        if (node == null) {
            throw new IllegalArgumentException("Node not found");
        }
        Queue<Node> toVisit = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        toVisit.add(node);
        visited.add(node);
        while (!toVisit.isEmpty()) {
            Node currentNode = toVisit.poll();
            for (Node neighbor : currentNode.neighborNodes().toList()) {
                System.out.println("Current Node: " +currentNode + "\nNeighbors of current Node: " + currentNode.neighborNodes().toList() + "\nVisited Nodes: " + visited + "\n");
                if (!visited.contains(neighbor)) {
                    toVisit.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return visited;
    }

    public static Pair<List<Node>, Integer> shortestPath(Graph graph, String startNodeId, String endNodeId) {
        Node startNode = graph.getNode(startNodeId);
        Node endNode = graph.getNode(endNodeId);
        if (startNode == null || endNode == null) {
            throw new IllegalArgumentException("Start or end node not found in the graph.");
        }

        Queue<Node> toVisit = new LinkedList<>();
        HashMap<Node, Node> predecessors = new HashMap<>();
        HashMap<Node, Integer> distances = new HashMap<>();

        toVisit.add(startNode);
        distances.put(startNode, 0);

        while (!toVisit.isEmpty()) {
            Node currentNode = toVisit.poll();

            if (currentNode.equals(endNode)) {
                break;
            }

            for (Node neighbor : currentNode.neighborNodes().toList()) {
                if (!distances.containsKey(neighbor)) {
                    toVisit.add(neighbor);
                    predecessors.put(neighbor, currentNode);
                    distances.put(neighbor, distances.get(currentNode) + 1);
                }
            }
        }

        List<Node> path = new ArrayList<>();
        Node currentNode = endNode;
        while (currentNode != null) {
            path.add(0, currentNode);
            currentNode = predecessors.get(currentNode);
        }

        return new Pair<>(path, distances.get(endNode));
    }

    public static void main(String[] args) {
        Graph graph = GraphLesen.readGraph("C:\\Users\\andre\\Desktop\\GraphPraktikum\\src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph07.gka");
        Pair<List<Node>, Integer> pair = shortestPath(graph, "v4", "v5");
        System.setProperty("org.graphstream.ui", "swing");
        graph.display();
        System.out.println(pair.second);
        System.out.println(pair.first);
    }
}
