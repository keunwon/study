package algorithm.programmers

import java.util.*

class Lesson159993 {
    fun solution(maps: Array<String>): Int {
        var start: Node? = null
        var lever: Node? = null
        var end: Node? = null

        for (i in maps.indices) {
            for ((j, type) in maps[i].withIndex()) {
                when (type) {
                    'S' -> start = Node(i, j, 0)
                    'L' -> lever = Node(i, j, 0)
                    'E' -> end = Node(i, j, 0)
                }
            }
        }

        val dist1 = if (start != null && lever != null) bfs(start, lever, maps) else -1
        val dist2 = if (lever != null && end != null) bfs(lever, end, maps) else -1

        if (dist1 == -1 || dist2 == -1) return -1
        return dist1 + dist2
    }

    private fun bfs(start: Node, end: Node, maps: Array<String>): Int {
        val queue = LinkedList<Node>().apply { offer(start) }
        val visited = Array(maps.size) { BooleanArray(maps[0].length) }.apply {
            this[start.r][start.c] = true
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.r == end.r && cur.c == end.c) return cur.d

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in maps.indices || nc !in maps[0].indices) continue
                if (visited[nr][nc] || maps[nr][nc] == 'X') continue

                visited[nr][nc] = true
                queue.offer(Node(nr, nc, cur.d + 1))
            }
        }
        return -1
    }

    private data class Node(val r: Int, val c: Int, val d: Int)

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}
