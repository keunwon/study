package com.keunwon.algorithm.baekjoon

/**
 * Title: 최소 스패닝 트리
 * Level: 골드-4
 **/
class Problem1197 {
    private lateinit var parent: IntArray

    fun solution(v: Int, e: Int, arr: Array<IntArray>): Int {
        this.parent = IntArray(v + 1) { it }

        arr.sortWith(compareBy { it[2] })

        var answer = 0
        for ((a, b, c) in arr) {
            val find1 = find(a)
            val find2 = find(b)

            if (find1 == find2) continue
            union(a, b)
            answer += c
        }
        return answer
    }

    private fun find(num: Int): Int {
        return if (parent[num] == num) num else find(parent[num])
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        return if (find1 < find2) parent[find2] = find1
        else parent[find1] = find2
    }
}

fun main() {
    val (v, e) = readln()!!.split(" ").map { it.toInt() }
    val arr = Array(e) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }
    Problem1197().solution(v, e, arr).also(::println)
}
