package dijkstra;

public class App {
    public static void main(String[] args) {
        Vertex vertexA = new Vertex("A");
        Vertex vertexB = new Vertex("B");
        Vertex vertexC = new Vertex("C");
        Vertex vertexD = new Vertex("D");
        Vertex vertexE = new Vertex("E");
        Vertex vertexF = new Vertex("F");
        Vertex vertexG = new Vertex("G");
        Vertex vertexH = new Vertex("H");

        vertexA.addNeighbour(new Edge(vertexA, vertexB, 5));
        vertexA.addNeighbour(new Edge(vertexA, vertexH, 8));
        vertexA.addNeighbour(new Edge(vertexA, vertexE, 9));

        vertexB.addNeighbour(new Edge(vertexB, vertexC, 12));
        vertexB.addNeighbour(new Edge(vertexB, vertexD, 15));
        vertexB.addNeighbour(new Edge(vertexB, vertexH, 4));

        vertexC.addNeighbour(new Edge(vertexC, vertexD, 3));
        vertexC.addNeighbour(new Edge(vertexC, vertexG,11));

        vertexH.addNeighbour(new Edge(vertexH, vertexC, 7));
        vertexH.addNeighbour(new Edge(vertexH, vertexF, 6));

        vertexE.addNeighbour(new Edge(vertexE, vertexH, 5));
        vertexE.addNeighbour(new Edge(vertexE, vertexF, 4));
        vertexE.addNeighbour(new Edge(vertexE, vertexG, 20));

        vertexF.addNeighbour(new Edge(vertexF, vertexC, 1));
        vertexF.addNeighbour(new Edge(vertexF, vertexG, 13));

        vertexD.addNeighbour(new Edge(vertexD, vertexG, 9));

        DijkstraAlgo algo = new DijkstraAlgo();
        algo.computePath(vertexA);

        System.out.println(algo.getShortestPathTo(vertexD));
    }
}
