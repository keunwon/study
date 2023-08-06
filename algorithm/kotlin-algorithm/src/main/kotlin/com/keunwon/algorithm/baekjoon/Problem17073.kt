package com.keunwon.algorithm.baekjoon

/**
 * Title: 나무 위의 빗물
 * Level: 골드-5
 **/
class Problem17073 {
    fun solution(n: Int, w: Int, arr: Array<Pair<Int, Int>>): Double {
        val graph = Array(n + 1) { mutableListOf<Int>() }.apply {
            arr.forEach { (a, b) ->
                this[a].add(b)
                this[b].add(a)
            }
        }

        var count = 0
        for (i in 2 until graph.size) {
            if (graph[i].size == 1) count++
        }
        return w.toDouble() / count
    }
}

fun main() {
    val (n, w) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(n - 1) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b) -> a to b }
    }

    Problem17073().solution(n, w, arr).also(::println)
}
