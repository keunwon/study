package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 미로 탈출 명령어
 * Level: 3
 **/
class Lessons150365 {
    private val moves = arrayOf(
        Move("d", 1, 0),
        Move("l", 0, -1),
        Move("r", 0, 1),
        Move("u", -1, 0),
    )

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val visited = Array(n + 1) { Array(m + 1) { BooleanArray(k + 1) } }
        val queue = LinkedList<Node>()

        visited[x][y][0] = true
        queue.offer(Node(x, y, ""))

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (k == cur.command.length) {
                if (cur.r == r && cur.c == c) return cur.command
                continue
            }

            for ((dir, mr, mc) in moves) {
                val command = cur.command + dir
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in 1..n || nc !in 1..m) continue
                if (visited[nr][nc][command.length]) continue

                visited[nr][nc][command.length] = true
                queue.offer(Node(nr, nc, command))
            }
        }
        return "impossible"
    }

    private data class Move(val dir: String, val r: Int, val c: Int)

    private data class Node(val r: Int, val c: Int, val command: String)
}
