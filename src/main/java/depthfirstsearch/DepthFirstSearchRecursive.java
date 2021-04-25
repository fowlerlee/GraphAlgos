package depthfirstsearch;

import java.util.List;

public class DepthFirstSearchRecursive {

    public void dfs(List<Vertex> vertexList) {

        for (Vertex vertex : vertexList) {

            if (!vertex.isVisited()) {
                vertex.setVisited(true);

                dfsHelper(vertex);
            }
        }
    }

    private void dfsHelper(Vertex vertex) {

        for (Vertex vertexNeighbour : vertex.getNeighbours()){
            if(!vertexNeighbour.isVisited()){
                vertexNeighbour.setVisited(true);
                dfsHelper(vertexNeighbour);
            }
        }


    }
}
