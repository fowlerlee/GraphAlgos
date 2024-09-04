package prims;

import java.util.*;

public class PrimsAlgo {
    List<Edge> mst;
    PriorityQueue<Edge> heap;
    Set<Vertex> unvisited;
    private double fullCost;

    public PrimsAlgo(List<Vertex> vertexList) {
        this.unvisited = new HashSet<>(vertexList);
        this.mst = new ArrayList<>();
        this.heap = new PriorityQueue<>();
    }

    public void run(Vertex vertex) {
        unvisited.remove(vertex);
        while (!unvisited.isEmpty()) {

            for (Edge edge : vertex.getAdjacencyList()) {
                if (unvisited.contains(edge.getTargetVertex())) {
                    heap.add(edge);
                }
            }

            Edge minEdge = heap.remove();

            if (!unvisited.contains(minEdge.getTargetVertex())) {
                continue;
            }

            mst.add(minEdge);
            fullCost += minEdge.getWeight();

            vertex = minEdge.getTargetVertex();
            unvisited.remove(vertex);
        }
    }

    public void printSolution() {
        System.out.println("Cost of MST: " + this.fullCost);

        for (Edge edge : this.mst) {
            System.out.println(edge.getStartVertex() + "-" + edge.getTargetVertex());
        }
    }
}
