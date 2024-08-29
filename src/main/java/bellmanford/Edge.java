package bellmanford;

public class Edge {
    double cost;
    int from, to;

    public Edge(int from, int to, double cost){
        this.to = to;
        this.from = from;
        this.cost = cost;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

    public double getCost(){
        return cost;
    }
}
