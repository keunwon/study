package algorithm.leetcode

import java.util.LinkedList

class `200_Number_of_Islands` {
    fun numIslands(grid: Array<CharArray>): Int {
        val visited = Array(grid.size) { BooleanArray(grid[0].size) }
        var result = 0

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    bfs(Pair(i, j), visited, grid)
                    ++result
                }
            }
        }
        return result
    }

    private fun bfs(start: Pair<Int, Int>, visited: Array<BooleanArray>, grid: Array<CharArray>) {
        val queue = LinkedList<Pair<Int, Int>>().apply { offer(start) }
        visited[start.first][start.second] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mr, mc) in moves) {
                val nr = cur.first + mr
                val nc = cur.second + mc

                if (nr !in visited.indices || nc !in visited[0].indices) continue
                if (visited[nr][nc] || grid[nr][nc] == '0') continue

                visited[nr][nc] = true
                queue.offer(Pair(nr, nc))
            }
        }
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    `200_Number_of_Islands`().numIslands(
        arrayOf(
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('1', '1', '0', '1', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '0', '0', '0')
        )
    ).also { println(it) } // 1

    `200_Number_of_Islands`().numIslands(
        arrayOf(
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'),
            charArrayOf('0', '0', '0', '1', '1')
        )
    ).also { println(it) } // 3
}
