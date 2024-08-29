package kruskal;

public class Edge implements Comparable<Edge> {
   private Vertex startVertex;
   private Vertex targetVertex;
   private int weight;
   
public Edge(Vertex startVertex, Vertex targetVertex, int weight) {
    this.startVertex = startVertex;
    this.targetVertex = targetVertex;
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
public int getWeight() {
    return weight;
}
public void setWeight(int weight) {
    this.weight = weight;
}

@Override
public int compareTo(Edge o) {
    //compare based on weights
    return Double.compare(this.weight, o.getWeight());
}

@Override
public String toString() {
    return startVertex + " - " + targetVertex;
}

}
