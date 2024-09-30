package tarjan;

import java.util.List;
import java.util.Stack;

public class TarjanAlgorithm {

    private List<Vertex> graph;
    private Stack<Vertex> stack;
    private int index;
    private int sccCounter;

    public TarjanAlgorithm(List<Vertex> graph) {
        this.graph = graph;
        this.stack = new Stack<>();

    }

    public void run() {
        for (Vertex vertex : graph) {
            if (!vertex.isVisited()) {
                dfs(vertex);
            }
        }
    }

    private void dfs(Vertex vertex) {
        vertex.setIndex(index);
        vertex.setLowLink(index);
        index++;

        vertex.setVisited(true);
        stack.add(vertex);
        vertex.setOnStack(true);

        for (Vertex v : vertex.getAdjacencyList()) {
            if (!v.isVisited()) {
                dfs(v);
                // backtracking after dfs - we update the lowlink values
                vertex.setLowLink(Math.min(vertex.getLowLink(), v.getLowLink()));
            } else if (v.isOnStack()) {
                vertex.setLowLink(Math.min(v.getIndex(), vertex.getLowLink()));
            }
        }

        if (vertex.getIndex() == vertex.getLowLink()) {

            while (true) {
                Vertex w = stack.pop();
                w.setOnStack(false);
                w.setSccComponentId(sccCounter);

                if (w == vertex) {
                    break;
                }
            }
            sccCounter++;
        }
    }

    public void show() {
        for (Vertex vertex : graph) {
            System.out.println(vertex.toString());
        }
    }
}
