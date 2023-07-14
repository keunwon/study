package com.keunwon.algorithm.baekjoon

/**
 * Title: 올림픽
 * Level: 실버-5
 **/
class Problem8979 {
    fun solution(k: Int, arr: Array<IntArray>): Int {
        arr.sortWith(compareBy({ -it[1] }, { -it[2] }, { -it[3] }))

        if (arr[0][0] == k) return 1

        var rank = 1
        var step = 1
        for (i in 1 until arr.size) {
            val prev = arr[i - 1]
            val cur = arr[i]

            if (same(prev, cur)) step++
            else {
                rank += step
                step = 1
            }

            if (cur[0] == k) return rank
        }
        return rank
    }

    private fun same(arr1: IntArray, arr2: IntArray): Boolean {
        return arr1.slice(1..3) == arr2.slice(1..3)
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

    Problem8979().solution(k, arr).also { println(it) }
}
