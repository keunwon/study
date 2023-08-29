package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 모양 만들기
 * Level: 골드-3
 **/
class Problem16932 {
    private lateinit var map: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>

    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<IntArray>): Int {
        this.map = map
        this.visited = Array(map.size) { BooleanArray(map[0].size) }
        val areas = createAreas()
        val zeroQueue = createZeroQueue()
        var answer = 0

        while (zeroQueue.isNotEmpty()) {
            val cur = zeroQueue.poll()
            val size = bfsMerge(cur, areas)

            answer = answer.coerceAtLeast(size)
        }
        return answer
    }

    private fun bfsMerge(start: Node, areas: List<Int>): Int {
        val set = mutableSetOf<Int>()
        for ((mr, mc) in moves) {
            val nr = start.r + mr
            val nc = start.c + mc

            if (nr !in map.indices || nc !in map[0].indices) continue
            set.add(map[nr][nc])
        }
        return set.sumOf { areas[it] } + 1
    }

    private fun createAreas(): List<Int> {
        val areas = mutableListOf(0)
        var index = 1

        for (i in map.indices) {
            for ((j, num) in map[i].withIndex()) {
                if (visited[i][j] || num == 0) continue

                val size = bfs(Node(i, j), index++)
                areas.add(size)
                visited[i][j] = true
            }
        }
        return areas
    }

    private fun bfs(start: Node, index: Int): Int {
        val queue = LinkedList<Node>().apply { offer(start) }
        var size = 1

        with(start) {
            queue.offer(Node(r, c))
            visited[r][c] = true
            map[r][c] = index
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc] || map[nr][nc] == 0) continue

                queue.offer(Node(nr, nc))
                visited[nr][nc] = true
                map[nr][nc] = index
                size++
            }
        }
        return size
    }

    private fun createZeroQueue(): LinkedList<Node> {
        val queue = LinkedList<Node>()
        for (i in map.indices) {
            for ((j, num) in map[i].withIndex()) {
                if (num == 0) queue.offer(Node(i, j))
            }
        }
        return queue
    }

    private data class Node(val r: Int, val c: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(n) {
            val st = StringTokenizer(br.readLine())
            IntArray(m) { st.nextToken().toInt() }
        }

        Problem16932().solution(map).also { bw.write("$it") }
        bw.flush()
    }
}
