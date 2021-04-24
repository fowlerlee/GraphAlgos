package adjacencylistandmatrix;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private String name;
    private List<Vertex> adjacencyList;

    public Vertex(String name) {
        this.name = name;
        this.adjacencyList = new ArrayList<>();
    }

    public void addNeighbour(Vertex vertex) {
        adjacencyList.add(vertex);

    }

    public void showNeighbours() {
        for (Vertex vertex : adjacencyList) {
            System.out.println(vertex);
        }
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                '}';
    }
}
