package com.keunwon.algorithm.baekjoon

/**
 * Title: 입국심사
 * Level: 골드-5
 **/
class Problem3079 {
    fun solution(m: Int, arr: LongArray): Long {
        arr.sort()

        var left = arr.first()
        var right = arr.first() * m

        while (left <= right) {
            val mid = (left + right) / 2
            val sum = arr.sumOf { mid / it }

            if (m <= sum) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = LongArray(n) { readLine().toLong() }

    Problem3079().solution(m, arr).also { bw.write("$it") }

    bw.flush()
    bw.close()
    close()
}
