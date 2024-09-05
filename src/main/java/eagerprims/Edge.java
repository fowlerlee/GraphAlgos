package eagerprims;

class Edge {

    private Vertex start;
    private Vertex target;
    private double weight;

    public Edge(Vertex start, Vertex target, double weight) {
        this.start = start;
        this.target = target;
        this.weight = weight;
    }

    public Vertex getStart() {
        return start;
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
