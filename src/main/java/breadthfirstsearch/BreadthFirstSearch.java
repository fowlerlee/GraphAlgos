package breadthfirstsearch;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    public void traverse(Vertex vertex) {
        Queue<Vertex> queue = new LinkedList<>();

        vertex.setVisited(true);
        queue.add(vertex);

        while (!queue.isEmpty()) {

            Vertex actualVertex = queue.remove();
            System.out.println("Actual visited vertex: " + actualVertex);

            for (Vertex vert : actualVertex.getAdjacencyList()) {
                if (!vert.isVisited()) {
                    vert.setVisited(true);
                    queue.add(vert);
                }
            }

        }


    }
}
