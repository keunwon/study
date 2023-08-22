package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 합이 0인 네 정수
 * Level: 골드-2
 **/
class Problem7453 {
    fun solution(arr: Array<IntArray>): Long {
        val cd = IntArray(arr.size * arr.size)
        var index = 0

        for (i in arr.indices) {
            for (j in arr.indices) {
                cd[index++] = arr[i][2] + arr[j][3]
            }
        }
        cd.sort()

        var answer = 0L
        for (i in arr.indices) {
            for (j in arr.indices) {
                val num = arr[i][0] + arr[j][1]
                val upperIndex = upperBound(-num, cd)
                val lowerIndex = lowerBound(-num, cd)

                answer += upperIndex - lowerIndex
            }
        }
        return answer
    }

    private fun lowerBound(target: Int, arr: IntArray): Int {
        var left = 0
        var right = arr.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (target <= arr[mid]) right = mid - 1
            else left = mid + 1
        }
        return left
    }

    private fun upperBound(target: Int, arr: IntArray): Int {
        var left = 0
        var right = arr.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (target < arr[mid]) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val n = br.readLine().toInt()
        val arr = Array(n) {
            val st = StringTokenizer(br.readLine())
            IntArray(4) { st.nextToken().toInt() }
        }

        Problem7453().solution(arr).also { bw.write("$it") }
        bw.flush()
    }
}
