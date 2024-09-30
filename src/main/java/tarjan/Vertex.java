package tarjan;

import java.util.List;
import java.util.ArrayList;

public class Vertex {
    private String name;
    private List<Vertex> adjacencyList;
    private boolean visited;
    private boolean onStack;
    private int index;
    private int lowLink;
    private int sccComponentId;

    public Vertex(String name) {
        this.name = name;
        this.adjacencyList = new ArrayList<>();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isOnStack() {
        return onStack;
    }

    public void setOnStack(boolean onStack) {
        this.onStack = onStack;
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

    public void addNeighbor(Vertex e) {
        this.adjacencyList.add(e);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLowLink() {
        return lowLink;
    }

    public void setLowLink(int lowLink) {
        this.lowLink = lowLink;
    }

    public int getSccComponentId() {
        return sccComponentId;
    }

    public void setSccComponentId(int sccComponentId) {
        this.sccComponentId = sccComponentId;
    }

    @Override
    public String toString() {
        return "Vertex " + name + "is in SCC " + sccComponentId;
    }

}
