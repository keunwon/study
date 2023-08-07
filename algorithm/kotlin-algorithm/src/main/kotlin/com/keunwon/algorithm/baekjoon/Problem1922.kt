package com.keunwon.algorithm.baekjoon

/**
 * Title: 네트워크 연결
 * Level: 골드-4
 **/
class Problem1922 {
    private lateinit var parent: IntArray

    fun solution(n: Int, m: Int, arr: Array<Triple<Int, Int, Int>>): Int {
        this.parent = IntArray(n + 1) { it }

        arr.sortBy { it.third }

        return arr.sumOf { (a, b, c) ->
            if (find(a) != find(b)) {
                union(a, b)
                c
            } else 0
        }
    }

    private fun find(n: Int): Int {
        return if (parent[n] == n) n else find(parent[n])
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        return if (find1 < find2) parent[find2] = find1
        else parent[find1] = find2
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val arr = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    Problem1922().solution(n, m, arr).also(::println)
}
