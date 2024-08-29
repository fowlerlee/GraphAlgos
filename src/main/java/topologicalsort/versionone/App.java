package topologicalsort.versionone;
import java.util.List;
import java.util.Stack;

public class App {

    public static void main(String[] args) {
        Topological topological = new Topological();

        List<Vertex> graph = new Stack<>();
        graph.add(new Vertex("0"));
        graph.add(new Vertex("1"));
        graph.add(new Vertex("2"));
        graph.add(new Vertex("3"));
        graph.add(new Vertex("4"));
        graph.add(new Vertex("5"));

        graph.get(2).addNeighbour(graph.get(3));
        graph.get(3).addNeighbour(graph.get(1)); 
        graph.get(4).addNeighbour(graph.get(0));
        graph.get(4).addNeighbour(graph.get(1));
        graph.get(5).addNeighbour(graph.get(0));
        graph.get(5).addNeighbour(graph.get(2));

        for (Vertex vertex : graph) {
            if(!vertex.isVisited()){
                topological.dfs(vertex);
            }
        }
        Stack<Vertex> stack = topological.getStack();
        for (int i =0; i<graph.size();++i) {
            System.out.println(stack.pop().getName());
        }
    }
}