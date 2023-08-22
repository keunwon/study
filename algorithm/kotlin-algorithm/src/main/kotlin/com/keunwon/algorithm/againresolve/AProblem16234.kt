package com.keunwon.algorithm.againresolve

import java.util.*
import kotlin.math.abs

/**
 * Title: 인구 이동
 * Level: 골드-4
 **/
class AProblem16234 {
    private lateinit var arr: Array<IntArray>
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private var l = 0
    private var r = 0

    fun solution(l: Int, r: Int, arr: Array<IntArray>): Int {
        this.l = l
        this.r = r
        this.arr = arr
        var answer = 0

        while (true) {
            val visited = Array(arr.size) { BooleanArray(arr[0].size) }
            var flag = false

            for (i in arr.indices) {
                for (j in arr[0].indices) {
                    if (!visited[i][j] && bfs(i, j, visited)) flag = true
                }
            }

            if (!flag) break
            answer++
        }
        return answer
    }

    private fun bfs(sr: Int, sc: Int, visited: Array<BooleanArray>): Boolean {
        val queue = LinkedList<Node>()
        val set = mutableSetOf<Node>()

        with(Node(sr, sc)) {
            queue.offer(this)
            set.add(this)
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in arr.indices || nc !in arr[0].indices) continue
                if (visited[nr][nc] || !isMerge(arr[cur.r][cur.c], arr[nr][nc])) continue

                queue.offer(Node(nr, nc))
                set.add(Node(nr, nc))
                visited[nr][nc] = true
            }
        }

        if (set.size == 1) return false

        val num = set.sumOf { arr[it.r][it.c] } / set.size
        set.forEach { (r, c) -> arr[r][c] = num }
        return true
    }

    private fun isMerge(cur: Int, next: Int): Boolean {
        val diff = abs(cur - next)
        return diff in l..r
    }

    private data class Node(val r: Int, val c: Int)
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (n, l, r) = br.readLine().split(" ").map { it.toInt() }
        val arr = Array(n) {
            br.readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        AProblem16234().solution(l, r, arr).also { bw.write("$it") }
        bw.newLine()
        bw.flush()
    }
}
