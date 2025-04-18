package algorithm.leetcode

import java.util.LinkedList

class `994_Rotting_Oranges` {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun orangesRotting(grid: Array<IntArray>): Int {
        val queue = LinkedList<Pair<Int, Int>>()
        var freshCount = 0
        var time = 0

        for (i in grid.indices) {
            for ((j, type) in grid[i].withIndex()) {
                if (type == 1) ++freshCount
                else if (type == 2) queue.offer(Pair(i, j))
            }
        }

        while (queue.isNotEmpty()) {
            var size = queue.size

            while (size-- > 0) {
                val (r, c) = queue.poll()

                for ((mr, mc) in moves) {
                    val nr = r + mr
                    val nc = c + mc

                    if (nr in grid.indices && nc in grid[0].indices && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2
                        queue.offer(Pair(nr, nc))
                        --freshCount
                    }
                }
            }
            if (queue.size > 0) ++time
        }

        return if (freshCount == 0) time else -1
    }
}

fun main() {
    `994_Rotting_Oranges`().orangesRotting(
        arrayOf(
            intArrayOf(2, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(0, 1, 1)
        )
    ).also { println(it) } // 4

    `994_Rotting_Oranges`().orangesRotting(
        arrayOf(
            intArrayOf(2, 1, 1),
            intArrayOf(0, 1, 1),
            intArrayOf(1, 0, 1)
        )
    ).also { println(it) } // -1

    `994_Rotting_Oranges`().orangesRotting(
        arrayOf(intArrayOf(0, 2))
    ).also { println(it) } // 0
}
