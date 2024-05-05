package Aufgabe1;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.*;
import java.util.stream.Collectors;

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
            for (Node neighbor : currentNode.neighborNodes().collect(Collectors.toSet())) {
                System.out.println("Current Node: " +currentNode + "\nNeighbors of current Node: " + currentNode.neighborNodes().toList() + "\nVisited Nodes: " + visited + "\n");
                if (!visited.contains(neighbor)) {
                    toVisit.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return visited;
    }

    /**
     * Find the shortest path from start node to end node in a graph.
     * @param graph The graph to search in.
     * @param startNodeId The id of the start node.
     * @param endNodeId The id of the end node.
     * @return
     */
    public static Pair<List<Node>, Integer> shortestPath(Graph graph, String startNodeId, String endNodeId) {
        Node startNode = graph.getNode(startNodeId);
        Node endNode = graph.getNode(endNodeId);
        if (startNode == null || endNode == null) {
            throw new IllegalArgumentException("Start or end node not found in the graph.");
        }

        //Nodes to visit
        Queue<Node> toVisit = new LinkedList<>();
        //Nodes
        HashMap<Node, Node> predecessors = new HashMap<>();
        //Store all distances from start node to each node and the already visited ones
        HashMap<Node, Integer> distances = new HashMap<>();

        //Initialize queue with start node
        toVisit.add(startNode);
        //Initialize distance to start node with 0 because it is the start node
        distances.put(startNode, 0);

        //While there are nodes to visit
        while (!toVisit.isEmpty()) {

            //Get the current node from the queue and remove it from the head of queue
            Node currentNode = toVisit.poll();

            //check if it is the end node and break the loop
            if (currentNode.equals(endNode)) {
                break;
            }

            //For each neighbor of the current node
            for (Node neighbor : currentNode.neighborNodes().collect(Collectors.toSet())) {

                //If the neighbor is not already visited
                if (!distances.containsKey(neighbor)) {
                    //Add the neighbor to the queue
                    toVisit.add(neighbor);
                    //Set the predecessor of the neighbor to the current node
                    predecessors.put(neighbor, currentNode);
                    //Set the distance from the start node to the neighbor
                    distances.put(neighbor, distances.get(currentNode) + 1);
                }
            }
        }
        if (!distances.containsKey(endNode)) {
            throw new IllegalArgumentException("No path found from start node to end node.");
        }
        List<Node> path = new ArrayList<>();
        Node currentNode = endNode;
        //Reconstructs the path from the end node to the start node
        while (currentNode != null) {
            path.add(0, currentNode);
            currentNode = predecessors.get(currentNode);
        }
        return new Pair<>(path, distances.get(endNode));
    }

    public static void main(String[] args) {
        Graph graph = GraphLesen.readGraph("C:\\Users\\andre\\Desktop\\GraphPraktikum\\src\\main\\java\\Aufgabe1\\Dateien_1_gka\\graph01.gka");
        Pair<List<Node>, Integer> pair = shortestPath(graph, "a", "e");
        System.setProperty("org.graphstream.ui", "swing");
        graph.display();
        System.out.println(pair.second);
        System.out.println(pair.first);
    }
}
