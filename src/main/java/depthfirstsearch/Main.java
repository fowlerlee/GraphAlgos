package depthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");
        Vertex v5 = new Vertex("E");


        List<Vertex> list = new ArrayList<>();
        v1.addNeighbour(v2);
        v1.addNeighbour(v3);
        v3.addNeighbour(v4);
        v4.addNeighbour(v5);


        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);


        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        depthFirstSearch.dfs(list);

    }


}
