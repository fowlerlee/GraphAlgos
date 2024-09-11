package kosaraju;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int id;
    private String name;
    private List<Vertex> adjacencyList;
    private boolean visited;
    private int componentId;

    public Vertex(int id, String name) {
        this.id = id;
        this.name = name;
        this.adjacencyList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    public void addNeighbour(Vertex vertex) {
        this.adjacencyList.add(vertex);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    @Override
    public String toString() {
        return name;
    }

}
