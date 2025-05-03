package algorithm.leetcode

import java.util.LinkedList

class `0_LeetCode` {
    fun numIslands(grid: Array<CharArray>): Int {
        val visited = Array(grid.size) { BooleanArray(grid[0].size) }
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        var result = 0

        for (i in grid.indices) {
            for ((j, type) in grid[i].withIndex()) {
                if (type == '1' && !visited[i][j]) {
                    ++result

                    val q = LinkedList<Pair<Int, Int>>().apply { offer(i to j) }
                    visited[i][j] = true

                    while (q.isNotEmpty()) {
                        val (r, c) = q.poll()

                        for ((mr, mc) in moves) {
                            val nr = r + mr
                            val nc = c + mc

                            if (nr in grid.indices && nc in grid[0].indices && grid[nr][nc] == '1' && !visited[nr][nc]) {
                                visited[nr][nc] = true
                                q.offer(nr to nc)
                            }
                        }
                    }
                }
            }
        }
        return result
    }
}
