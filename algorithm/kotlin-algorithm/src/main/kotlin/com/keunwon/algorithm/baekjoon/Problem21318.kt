package com.keunwon.algorithm.baekjoon

/**
 * Title: 피아노 체조
 * Level: 실버-1
 **/
class Problem21318 {
    fun solution(arr: IntArray, questions: Array<Pair<Int, Int>>): IntArray {
        val prefixSum = IntArray(arr.size + 1)
        val numbers = intArrayOf(0, *arr)

        for (i in 1 until prefixSum.size) {
            prefixSum[i] = prefixSum[i - 1]
            if (numbers[i - 1] > numbers[i]) prefixSum[i]++
        }
        return questions.map { (s, e) -> prefixSum[e] - prefixSum[s] }
            .toIntArray()
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val n = readLine().toInt()
    val arr1 = readLine().split(" ").map { it.toInt() }.toIntArray()
    val q = readLine().toInt()
    val questions = Array(q) {
        readLine().split(" ").let { it[0].toInt() to it[1].toInt() }
    }

    Problem21318().solution(arr1, questions).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
