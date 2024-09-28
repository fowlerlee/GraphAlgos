package leetcode;

public class PathExists {
    class Solution {
        int par[];
        int size[];
        int rank[];

        public boolean validPath(int n, int[][] edges, int source, int destination) {
            par = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                par[i] = i;
            }
            for (int[] a : edges) {
                int x = find(a[0]);
                int y = find(a[1]);
                if (x != y) {
                    union(x, y);
                }
            }
            if (find(source) == find(destination))
                return true;
            else
                return false;
        }

        int find(int a) {
            if (par[a] == a)
                return a;
            else
                return par[a] = find(par[a]);
        }

        void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b)
                return;
            if (rank[a] >= rank[b]) {
                par[b] = a;
                rank[a]++;
            } else {
                par[a] = b;
                rank[b]++;
            }
        }
    }
}
