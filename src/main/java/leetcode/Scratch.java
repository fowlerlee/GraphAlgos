package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Scratch {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int[] edge : edges) {
                if (edge[0] == current) {
                    int neighbor = edge[1];
                    if (neighbor == destination) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scratch s = new Scratch();
        int[][] edges = new int[][]{{0,1},{1,2},{2,0}};
        boolean result = s.validPath(3, edges, 0, 2);
        System.out.println(result);
    }
}

