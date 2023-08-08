package com.keunwon.algorithm.baekjoon

/**
 * Title: 도시 분할 계획
 * Level: 골드-4
 **/
class Problem1647 {
    private lateinit var parent: IntArray

    fun solution(n: Int, arr: Array<Triple<Int, Int, Int>>): Int {
        this.parent = IntArray(n + 1) { it }
        var shortestDistance = 0
        var lastDistance = 0

        arr.sortBy { it.third }

        for ((a, b, c) in arr) {
            if (find(a) != find(b)) {
                union(a, b)
                shortestDistance += c
                lastDistance = c
            }
        }
        return shortestDistance - lastDistance
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
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    Problem1647().solution(n, arr).also(::println)
}
