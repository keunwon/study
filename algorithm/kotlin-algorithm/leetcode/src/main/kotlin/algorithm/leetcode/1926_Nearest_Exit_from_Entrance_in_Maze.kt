package algorithm.leetcode

import java.util.LinkedList

class `1926_Nearest_Exit_from_Entrance_in_Maze` {
    fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
        val queue = LinkedList<Node>().apply { offer(Node(entrance[0], entrance[1], -1)) }
        val visited = Array(maze.size) { BooleanArray(maze[0].size) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r !in maze.indices || cur.c !in maze[0].indices) {
                if (cur.d > 0) return cur.d
                continue
            }

            if (visited[cur.r][cur.c] || maze[cur.r][cur.c] == '+') continue

            visited[cur.r][cur.c] = true
            queue.offer(Node(cur.r - 1, cur.c, cur.d + 1))
            queue.offer(Node(cur.r, cur.c + 1, cur.d + 1))
            queue.offer(Node(cur.r + 1, cur.c, cur.d + 1))
            queue.offer(Node(cur.r, cur.c - 1, cur.d + 1))
        }
        return -1
    }

    data class Node(val r: Int, val c: Int, val d: Int)
}

fun main() {
    `1926_Nearest_Exit_from_Entrance_in_Maze`().nearestExit(
        arrayOf(
            charArrayOf('+', '+', '.', '+'),
            charArrayOf('.', '.', '.', '+'),
            charArrayOf('+', '+', '+', '.'),
        ),
        intArrayOf(1, 2)
    ).also { println(it) } // 1

    `1926_Nearest_Exit_from_Entrance_in_Maze`().nearestExit(
        arrayOf(
            charArrayOf('+', '+', '+'),
            charArrayOf('.', '.', '.'),
            charArrayOf('+', '+', '+')
        ),
        intArrayOf(1, 0)
    ).also { println(it) } // 2
}
