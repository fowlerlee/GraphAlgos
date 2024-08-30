package kruskal;

import java.util.List;

public class DisjointSet {
    public DisjointSet(List<Vertex> rootNodes) {
        makeSets(rootNodes);
    }

    // find the node and perform path compression to get O(1) 
    public Node find(Node node){
        Node actual = node;

        // find the representative (root) node
        while(actual.getParentNode() != null){
            actual = actual.getParentNode();
        }
        
        Node root = actual;
        actual = node;

        //path compression makes sure when we look for the representative
        // we will do this in O(1) time complexity
        while (actual != root) {
            Node temp = actual.getParentNode();
            actual.setParentNode(root);
            actual = temp;
        }

        return root;
    }



    public void union(Node node1, Node node2){
        Node root1 = find(node1);
        Node root2 = find(node2);

        if(root1 == root2){
            return;
        }

        if(root1.getHeight() < root2.getHeight()){
            root1.setParentNode(root2);

        } else if(root2.getHeight() < root1.getHeight()) {
            root2.setParentNode(root1);
        } else {
            root2.setParentNode(root1);
            root1.setHeight(root1.getHeight() + 1);
        }
    }

    private void makeSets(List<Vertex> listOfNodes) {
        for(Vertex vertex: listOfNodes){
            makeSet(vertex);
        }
    }

    private void makeSet(Vertex vertex) {
        Node node = new Node(0, null);
        vertex.setNode(node);
    }
    
}
