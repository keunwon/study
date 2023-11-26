package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons150365 {
    private val moves = arrayOf(
        Move('d', 1, 0),
        Move('l', 0, -1),
        Move('r', 0, 1),
        Move('u', -1, 0)
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

            for (move in moves) {
                val nr = cur.r + move.r
                val nc = cur.c + move.c
                val command = "${cur.command}${move.dir}"

                if (nr !in 1..n || nc !in 1..m) continue
                if (visited[nr][nc][command.length]) continue

                visited[nr][nc][command.length] = true
                queue.offer(Node(nr, nc, command))
            }
        }
        return "impossible"
    }

    private data class Move(val dir: Char, val r: Int, val c: Int)

    private data class Node(val r: Int, val c: Int, val command: String)
}

fun main() {
    ALessons150365().solution(
        3, 4, 2, 3, 3, 1, 5
    ).also(::println)

    ALessons150365().solution(
        2, 2, 1, 1, 2, 2, 2
    ).also(::println)

    ALessons150365().solution(
        3, 3, 1, 2, 3, 3, 4
    ).also(::println)
}
