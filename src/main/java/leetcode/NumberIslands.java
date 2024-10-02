package leetcode;

public class NumberIslands {

    /*
     * Input: grid = [
     * ["1","1","1","1","0"],
     * ["1","1","0","1","0"],
     * ["1","1","0","0","0"],
     * ["0","0","0","0","0"]
     * ]
     * Output = 1
     * 
     * Input: grid = [
     * ["1","1","0","0","0"],
     * ["1","1","0","0","0"],
     * ["0","0","1","0","0"],
     * ["0","0","0","1","1"]
     * ]
     * Output: 3
     */
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int index = 0;
        int lowLink = 0;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    index++;
                    lowLink++;
                }
            }
        }
        return 1;
    }
}
