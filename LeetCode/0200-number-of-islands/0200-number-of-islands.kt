class Solution {
    fun numIslands(grid: Array<CharArray>): Int {
        var res = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '1') {
                    dfs(i, j, grid)
                    ++res
                }
            }
        }
        return res
    }

    private fun dfs(r: Int, c: Int, grid: Array<CharArray>) {
        if (r < 0 || r >= grid.size || c < 0 || c >= grid[0].size) {
            return
        }
        if (grid[r][c] == '0') {
            return
        }

        grid[r][c] = '0'
        dfs(r - 1, c, grid)
        dfs(r + 1, c, grid)
        dfs(r, c - 1, grid)
        dfs(r, c + 1, grid)
    }
}