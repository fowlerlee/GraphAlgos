package kruskal;

import java.util.*;


public class KruskalAlgo {
   public void run(List<Vertex> vertexList, List<Edge> edgeList) {
        DisjointSet disjointSet = new DisjointSet(vertexList);
        List<Edge> mst = new ArrayList<>();

        Collections.sort(edgeList);

        for (Edge edge : edgeList) {
            Vertex u = edge.getStartVertex();
            Vertex v = edge.getTargetVertex();

            if (disjointSet.find(u.getNode()) != disjointSet.find(v.getNode())) {
               mst.add(edge); 
            }

            disjointSet.union(u.getNode(), v.getNode());
        }

        for (Edge edge : mst) {
           System.out.println(edge);
        }
   }
}
