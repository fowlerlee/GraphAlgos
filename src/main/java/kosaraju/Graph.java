package kosaraju;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Edge> edgeList;
    private List<Vertex> vertexList;

    public Graph() {

    }

    public Graph(List<Vertex> vertexList, List<Edge> edgeList) {
        this.edgeList = edgeList;
        this.vertexList = vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public Graph getTransposedGraph() {
        Graph transposedGraph = new Graph();
        List<Vertex> transposedList = new ArrayList<>();

        for (Vertex vertex : this.vertexList) {
            transposedList.add(vertex);
        }

        for (Edge edge : this.edgeList) {
            transposedList.get(edge.getTargetVertex().getId()).addNeighbour(edge.getStartVertex());
        }

        transposedGraph.setVertexList(transposedList);

        return transposedGraph;
    }
}
