package com.keunwon.algorithm.baekjoon

/**
 * Title: 역사
 * Level: 골드-3
 **/
class Problem1613 {
    fun solution(n: Int, arr1: Array<Pair<Int, Int>>, arr2: Array<Pair<Int, Int>>): IntArray {
        val dist = createDistArray(n, arr1).apply { dijkstra(n, this) }
        return arr2.map { (a, b) -> dist[a][b] }.toIntArray()
    }

    private fun dijkstra(n: Int, dist: Array<IntArray>) {
        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    if (dist[j][i] == 1 && dist[i][k] == 1) {
                        dist[j][k] = 1
                    }
                    if (dist[j][i] == -1 && dist[i][k] == -1) {
                        dist[j][k] = -1
                    }
                }
            }
        }
    }

    private fun createDistArray(n: Int, arr1: Array<Pair<Int, Int>>): Array<IntArray> {
        return Array(n + 1) { IntArray(n + 1) }.apply {
            arr1.forEach { (a, b) ->
                this[a][b] = -1
                this[b][a] = 1
            }
        }
    }
}

fun main() {
    val readIntArray = {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }
    val (n, k) = readIntArray()
    val arr1 = Array(k) { readIntArray().let { it[0] to it[1] } }
    val s = readLine()!!.toInt()
    val arr2 = Array(s) { readIntArray().let { it[0] to it[1] } }

    Problem1613().solution(n, arr1, arr2).forEach(::println)
}
