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
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int countOfIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    countOfIslands++;
                    dfs(grid, i, j);
                }
            }
        }
        return countOfIslands;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || j >= grid[0].length || i >= grid.length || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0';
        dfs(grid, i + 1, j); // up
        dfs(grid, i - 1, j); // down
        dfs(grid, i, j + 1); // right
        dfs(grid, i, j - 1); // left
    }

    public static void main(String[] args) {
        NumberIslands soln = new NumberIslands();
        char[][] grid = {
                { '1', '1', '0', '1', '1' },
                { '0', '0', '0', '0', '0' },
                { '1', '1', '0', '1', '1' },
                { '0', '0', '0', '0', '0' } };

        int i = soln.numIslands(grid);
        System.out.println("num of islands: " + i);

    }

}
