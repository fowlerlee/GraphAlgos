package dijkstra;

import java.util.*;

public class DijkstraAlgo {
    private PriorityQueue<Vertex> pq;

    public void computePath(Vertex startVertex){

        startVertex.setDistance(0);
        pq = new PriorityQueue<>();
        pq.add(startVertex);

        while(!pq.isEmpty()){

            Vertex actualVertex = pq.poll();

            for (Edge edge : actualVertex.getAdjacencyList()) {
                Vertex u = edge.getStartVertex();
                Vertex v = edge.getTargetVertex();

                double d = actualVertex.getDistance() + edge.getWeight();
                
                if (d < v.getDistance()){
                    pq.remove(v);
                    v.setDistance(d);
                    v.setPredecessor(actualVertex);
                    pq.add(v);
                }
            }
         }
    }

    public List<Vertex> getShortestPathTo(Vertex targetVertex) {
        List<Vertex> shortestPath = new ArrayList<>();

        for (Vertex vertex = targetVertex; vertex!=null; vertex = vertex.getPredecessor()){
            shortestPath.add(vertex);
        } 
        
        Collections.reverse(shortestPath);
        return shortestPath;
    }
    
}