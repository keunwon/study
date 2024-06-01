package com.keunwon.algorithm.programmers

import java.util.*

// todo 메모리 사용량이 높음
class Lesson150365 {
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val queue = LinkedList<Node>().apply { offer(Node(x, y, "")) }
        val visited = Array(n + 1) { Array(m + 1) { BooleanArray(k + 1) } }.apply {
            this[x][y][0] = true
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.route.length == k) {
                if (cur.r == r && cur.c == c) return cur.route
                continue
            }

            for ((dir, mr, mc) in moves) {
                val route = cur.route + dir
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in 1..n || nc !in 1..m) continue
                if (visited[nr][nc][route.length]) continue

                visited[nr][nc][route.length] = true
                queue.offer(Node(nr, nc, route))
            }
        }
        return "impossible"
    }

    private data class Node(val r: Int, val c: Int, val route: String)

    companion object {
        private val moves = arrayOf(
            Triple('d', 1, 0),
            Triple('l', 0, -1),
            Triple('r', 0, 1),
            Triple('u', -1, 0),
        )
    }
}