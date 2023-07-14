package com.keunwon.algorithm.baekjoon

/**
 * Title: 덩치
 * Level: 실버-5
 **/
class Problem7568 {
    fun solution(arr: Array<IntArray>): IntArray {
        val answer = mutableListOf<Int>()

        for (i in arr.indices) {
            var rank = 1

            for (j in arr.indices) {
                if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) rank++
            }
            answer.add(rank)
        }
        return answer.toIntArray()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

    Problem7568().solution(arr).also { println(it.joinToString(" ")) }
}
