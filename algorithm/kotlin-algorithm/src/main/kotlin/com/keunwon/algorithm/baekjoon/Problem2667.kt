package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 단지번호붙이기
 * Level: 실버-1
 **/
class Problem2667 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(map: Array<IntArray>): List<Int> {
        val visited = Array(map.size) { BooleanArray(map[0].size) }
        val answer = mutableListOf<Int>()

        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    val count = bfs(Node(i, j), map, visited)
                    answer.add(count)
                }
            }
        }
        return answer.sorted()
    }

    private fun bfs(start: Node, map: Array<IntArray>, visited: Array<BooleanArray>): Int {
        val queue = LinkedList<Node>()
        var count = 0

        queue.offer(start)
        visited[start.r][start.c] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            count++

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (map[nr][nc] == 0 || visited[nr][nc]) continue

                visited[nr][nc] = true
                queue.offer(Node(nr, nc))
            }
        }
        return count
    }

    private data class Node(val r: Int, val c: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val n = br.readLine().toInt()
        val map = Array(n) {
            br.readLine().toCharArray().map { it.digitToInt() }.toIntArray()
        }

        val list = Problem2667().solution(map)
        bw.write("${list.size}")
        bw.newLine()

        list.forEach {
            bw.write("$it")
            bw.newLine()
        }

        bw.flush()
    }
}
