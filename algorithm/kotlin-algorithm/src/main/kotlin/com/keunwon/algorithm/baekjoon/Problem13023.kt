package com.keunwon.algorithm.baekjoon

/**
 * Title: ABCDE
 * Level: 골드-5
 **/
class Problem13023 {
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var viisted: BooleanArray
    private var answer = 0

    fun solution(n: Int, arr: Array<IntArray>): Int {
        this.viisted = BooleanArray(n)
        this.graph = Array(n) { mutableListOf() }

        arr.forEach { (a, b) ->
            graph[a].add(b)
            graph[b].add(a)
        }

        for (i in 0 until n) {
            if (answer == 0) dfs(i, 1)
        }
        return answer
    }

    private fun dfs(start: Int, depth: Int) {
        if (depth == 5) {
            answer = 1
            return
        }

        viisted[start] = true

        for (next in graph[start]) {
            if (!viisted[next]) dfs(next, depth + 1)
        }

        viisted[start] = false
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem13023().solution(n, arr).also(::println)
}
