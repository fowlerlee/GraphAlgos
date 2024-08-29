package bellmanford;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bellmanford
 */
public class Bellmanford {

    public static List<Edge>[] createBellmanFordOfSize(int size){
         @SuppressWarnings("unchecked")
        List<Edge>[] graph = new List[size];
         for (int i = 0; i < size; i++) {
            graph[i] = new ArrayList<>();
        }
        return graph;
    }

    public static void addEdge(List<Edge>[] graph, int from, int to, double cost){
        graph[from].add(new Edge(from, to, cost));
    }

    public static double[] bellmanford(List<Edge>[] graph, int v, int start){
        double[] dist = new double[v];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;

        for (int i = 0; i < dist.length; i++) {
            for (List<Edge> edges : graph) {
                for (Edge edge : edges) {
                    if (dist[edge.from] + edge.cost < dist[edge.to]) {
                        dist[edge.to] = dist[edge.from] + edge.cost;
                    }
                    
                }
            }
        }

        return new double[1];
    }
}