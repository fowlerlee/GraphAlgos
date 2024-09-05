package eagerprims;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex
 */
public class Vertex implements Comparable<Vertex> {

    private String name;
    private Edge minEdge;
    private double distance = Double.MAX_VALUE;
    private List<Edge> adjacencies;
    private boolean visited;

    public Vertex(String name) {
        this.name = name;
        this.adjacencies = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        this.adjacencies.add(edge);
    }

    public List<Edge> getAdjacencylist() {
        return this.adjacencies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Edge getMinEdge() {
        return minEdge;
    }

    public void setMinEdge(Edge minEdge) {
        this.minEdge = minEdge;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setVisited(boolean b) {
        this.visited = b;
    }

    public boolean isVisited() {
        return this.visited;
    }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(this.distance, other.getDistance());
    }

    @Override
    public String toString() {
        return name + " - " + this.distance;
    }

}