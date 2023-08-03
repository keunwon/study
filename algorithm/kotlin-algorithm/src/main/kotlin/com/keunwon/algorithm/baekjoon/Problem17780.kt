package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 새로운 게임
 * Level: 골드-2
 **/
class Problem17780 {
    private lateinit var deque: Array<Array<Deque<Int>>>
    private lateinit var nodes: MutableList<Node>

    fun solution(arr: Array<IntArray>, targets: Array<Triple<Int, Int, Int>>): Int {
        this.deque = Array(arr.size) { Array(arr.size) { ArrayDeque<Int>() } }
        this.nodes = targets.mapIndexed { index, (r, c, d) ->
            deque[r - 1][c - 1].offer(index)
            Node(r - 1, c - 1, d)
        }.toMutableList()

        var count = 1
        while (count <= 1000) {
            for ((i, node) in nodes.withIndex()) {
                if (deque[node.r][node.c].first() != i) continue

                var (nr, nc) = nextPosition(node)

                if (nr !in arr.indices || nc !in arr[0].indices || arr[nr][nc] == BLUE) {
                    node.reverseDir()

                    nextPosition(node).let {
                        nr = it.first
                        nc = it.second
                    }
                    if (nr !in arr.indices || nc !in arr[0].indices || arr[nr][nc] == BLUE) {
                        continue
                    }
                }

                when (arr[nr][nc]) {
                    RED -> moveRed(node, nr, nc)
                    WHITE -> moveWhite(node, nr, nc)
                }

                if (deque[nr][nc].size >= 4) return count
            }
            count++
        }
        return -1
    }

    private fun moveWhite(node: Node, nr: Int, nc: Int) {
        while (deque[node.r][node.c].isNotEmpty()) {
            val id = deque[node.r][node.c].poll()
            nodes[id] = Node(nr, nc, nodes[id].dir)
            deque[nr][nc].offer(id)
        }
    }

    private fun moveRed(node: Node, nr: Int, nc: Int) {
        while (deque[node.r][node.c].isNotEmpty()) {
            val id = deque[node.r][node.c].pollLast()
            nodes[id] = Node(nr, nc, nodes[id].dir)
            deque[nr][nc].offer(id)
        }
    }

    private fun nextPosition(node: Node): Pair<Int, Int> = when (node.dir) {
        1 -> node.r to node.c + 1
        2 -> node.r to node.c - 1
        3 -> node.r - 1 to node.c
        4 -> node.r + 1 to node.c
        else -> error("")
    }

    private data class Node(
        val r: Int,
        val c: Int,
        var dir: Int,
    ) {
        fun reverseDir() {
            dir = when (dir) {
                1 -> 2
                2 -> 1
                3 -> 4
                4 -> 3
                else -> error("")
            }
        }
    }

    companion object {
        private const val WHITE = 0
        private const val RED = 1
        private const val BLUE = 2
    }
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val arr = Array(n) {
        val st = StringTokenizer(readLine()!!)
        IntArray(n) { st.nextToken().toInt() }
    }
    val targets = Array(k) {
        val st = StringTokenizer(readLine())
        Triple(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
    }
    Problem17780().solution(arr, targets).also { println(it) }
}
