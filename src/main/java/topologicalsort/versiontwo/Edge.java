package topologicalsort.versiontwo;
import java.util.PriorityQueue;

public class Edge {
    private PriorityQueue<Edge> pq = new PriorityQueue<>();
    private Vertex target;
    private int weight;
    public Edge(Vertex target, int weight){
        this.target = target;
        this.weight = weight;
    }
    
    public Vertex getTarget() {
        return target;
    }
    public int getWeight() {
        return weight;
    }


}
