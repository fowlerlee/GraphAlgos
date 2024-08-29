package dijkstra;

public class Edge {
    private double weight;
    private Vertex startVertex;
    private Vertex targetVertex;
    
    public Edge(Vertex v1, Vertex v2, double weight){
        this.startVertex = v1;
        this.targetVertex = v2;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

}
