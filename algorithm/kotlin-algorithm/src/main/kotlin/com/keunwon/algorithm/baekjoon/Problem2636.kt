package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 치즈
 * Level: 골드-4
 **/
class Problem2636 {
    private lateinit var map: Array<IntArray>
    private val answer = intArrayOf(0, 0)
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<IntArray>): IntArray {
        this.map = map
        val oneCount = { map.sumOf { arr -> arr.count { it == 1 } } }
        answer[1] = oneCount()

        while (true) {
            if (bfs() > 0) {
                answer[0]++

                val count = oneCount()
                if (count != 0) answer[1] = count
            } else break
        }
        return answer
    }

    private fun bfs(): Int {
        val queue = LinkedList<Node>()
        val visited = Array(map.size) { BooleanArray(map[0].size) }
        var count = 0

        queue.offer(Node(0, 0))
        visited[0][0] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (map[cur.r][cur.c] == 1) {
                map[cur.r][cur.c] = 0
                count++
                continue
            }

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc]) continue

                queue.offer(Node(nr, nc))
                visited[nr][nc] = true
            }
        }
        return count
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

        Problem2636().solution(map).forEach {
            bw.write("$it")
            bw.newLine()
        }
        bw.flush()
    }
}
