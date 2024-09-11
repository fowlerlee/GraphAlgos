package kosaraju;

public class Kosaraju {
    private int count;
    private boolean[] marked;

    public Kosaraju(Graph graph) {
        DepthFirstOrder dfs = new DepthFirstOrder(graph.getTransposedGraph());
        marked = new boolean[graph.getVertexList().size()];

        for (Vertex v : dfs.getStack()) {
            if (!marked[v.getId()]) {
                dfs(v);
                count++;
            }
        }
    }

    private void dfs(Vertex vertex) {
        marked[vertex.getId()] = true;
        vertex.setComponentId(count);

        for (Vertex v : vertex.getAdjacencyList()) {
            if (!marked[v.getId()]) {
                dfs(v);
            }
        }
    }

    public int getCount() {
        return this.count;
    }

}
