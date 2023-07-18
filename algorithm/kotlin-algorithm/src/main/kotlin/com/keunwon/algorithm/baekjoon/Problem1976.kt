package com.keunwon.algorithm.baekjoon

/**
 * Title: 여행 가자
 * Level: 골드-4
 **/
class Problem1976 {
    fun solution(arr: Array<IntArray>, order: IntArray): String {
        val parent = IntArray(arr.size + 1) { it }
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                if (arr[i][j] == 1) union(parent, i, j)
            }
        }
        val idx = find(parent, order[0] - 1)
        return order.all { find(parent, it - 1) == idx }.let {
            if (it) "YES" else "NO"
        }
    }

    private fun find(parent: IntArray, n: Int): Int {
        return if (parent[n] == n) n else find(parent, parent[n])
    }

    private fun union(parent: IntArray, a: Int, b: Int) {
        val find1 = find(parent, a)
        val find2 = find(parent, b)

        if (find1 < find2) parent[find2] = find1
        else parent[find1] = find2
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val arr = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
    val order = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem1976().solution(arr, order).also { println(it) }
}
