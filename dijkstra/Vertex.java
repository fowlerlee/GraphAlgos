package dijkstra;

import java.util.*;

public class Vertex implements Comparable<Vertex> {
    private List<Edge> adjacencyList;
    private String name;
    private Vertex predecessor;
    private boolean visited;
    private double distance;

    public Vertex(String name){
        this.name  = name;
        adjacencyList = new ArrayList<>();
        this.distance = Double.MAX_VALUE;
    }

    public List<Edge> getAdjacencyList() {
        return adjacencyList;
    }


    public void addNeighbour(Edge edge) {
        this.adjacencyList.add(edge);
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Vertex getPredecessor() {
        return predecessor;
    }


    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }


    public boolean isVisited() {
        return visited;
    }


    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return name + "-" + distance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.distance, otherVertex.getDistance());
    }

}
