package eagerprims;

import java.util.List;
import java.util.PriorityQueue;

/**
 * EagerPrims
 */
class EagerPrims {

    private List<Vertex> vertexList;
    private PriorityQueue<Vertex> heap;

    public EagerPrims(List<Vertex> vertexList) {
        this.vertexList = vertexList;
        this.heap = new PriorityQueue<>();
    }

    public void compute(Vertex vertex) {
        vertex.setDistance(0);
        heap.add(vertex);

        while (!heap.isEmpty()) {
            Vertex v = heap.remove();
            searchVertex(v);
        }
    }

    private void searchVertex(Vertex vertex) {
        vertex.setVisited(true);
        for (Edge edge : vertex.getAdjacencylist()) {
            Vertex target = edge.getTarget();

            if (target.isVisited()) {
                continue;
            }

            if (edge.getWeight() < target.getDistance()) {
                target.setDistance(edge.getWeight());
                target.setMinEdge(edge);

                if (this.heap.contains(target)) {
                    this.heap.remove(target);
                }

                this.heap.add(target);
            }
        }
    }

    public void print() {
        double cost = 0;

        for (Vertex vertex : vertexList) {
            if (vertex.getMinEdge() != null) {
                Edge edge = vertex.getMinEdge();
                System.out.println("Edge: " + edge);
                cost += edge.getWeight();
            }
        }
        System.out.println("Cost of MST: " + cost);
    }

}