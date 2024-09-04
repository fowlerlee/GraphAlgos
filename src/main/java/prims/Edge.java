package prims;

public class Edge implements Comparable<Edge> {

    private double weight;
    private Vertex sourceVertex;
    private Vertex targetVertex;

    public Edge(double weight, Vertex sourceVertex, Vertex targetVertex) {
        this.weight = weight;
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getStartVertex() {
        return sourceVertex;
    }

    public void setStartVertex(Vertex sourceVertex) {
        this.sourceVertex = sourceVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.getWeight());
    }

}
