package depthfirstsearch;

import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {

    private Stack<Vertex> stack;

    public DepthFirstSearch() {
        this.stack = new Stack<>();
    }

    public void dfs(List<Vertex> vertexList) {

        for (Vertex vertex : vertexList) {

            if (!vertex.isVisited()) {
                vertex.setVisited(true);

                dfsHelper(vertex);
            }
        }
    }

    private void dfsHelper(Vertex rootVertex) {

        //LIFO structure
        stack.add(rootVertex);
        rootVertex.setVisited(true);

        while (!stack.isEmpty()) {
            Vertex actualVertex = stack.pop();
            System.out.println(actualVertex);

            for (Vertex vert : actualVertex.getNeighbours()) {
                if (!vert.isVisited()) {
                    vert.setVisited(true);
                    stack.add(vert);
                }
            }

        }
    }
}
