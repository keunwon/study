package com.keunwon.algorithm.baekjoon

/**
 * Title: 나만 안되는 연애
 * Level: 골드-3
 **/
class Problem14621 {
    private lateinit var parent: IntArray

    fun solution(genders: CharArray, edges: Array<Triple<Int, Int, Int>>): Int {
        edges.sortBy { it.third }

        this.parent = IntArray(genders.size + 1) { it }
        var answer = 0
        var count = 0

        for ((u, v, d) in edges) {
            if (find(u) != find(v) && genders[u - 1] != genders[v - 1]) {
                union(u, v)
                answer += d
                count++
            }
        }

        return if (count != genders.lastIndex) -1 else answer
    }

    private fun find(n: Int): Int {
        return if (parent[n] == n) n
        else {
            parent[n] = find(parent[n])
            parent[n]
        }
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        if (find1 != find2) parent[find2] = find1
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val genders = readLine()!!.replace(" ", "").toCharArray()
    val arr = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { (u, v, d) -> Triple(u, v, d) }
    }

    Problem14621().solution(genders, arr).also(::println)
}
