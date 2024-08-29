package versionone;
import java.util.ArrayList;
import java.util.List;

/**
 * Vertex
 */
public class Vertex {

    private String name;
    private List<Vertex> neighbourList;
    private boolean visited;

    public Vertex(String name){
        this.name = name;
        this.neighbourList = new ArrayList<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public void setVisited(boolean visited){
        this.visited = visited;
    }

    public void addNeighbour(Vertex v){
        this.neighbourList.add(v);
    }

    public List<Vertex> getNeighbourList(){
        return this.neighbourList;
    }
}