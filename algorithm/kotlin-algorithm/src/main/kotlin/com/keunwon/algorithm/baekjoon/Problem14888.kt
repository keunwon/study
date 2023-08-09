package com.keunwon.algorithm.baekjoon

/**
 * Title: 연산자 끼워넣기
 * Level: 실버-1
 **/
class Problem4888 {
    private lateinit var operatorCount: IntArray
    private lateinit var numbers: IntArray

    private val answer = intArrayOf(Int.MIN_VALUE, Int.MAX_VALUE)

    fun solution(numbers: IntArray, operatorCount: IntArray): IntArray {
        this.numbers = numbers
        this.operatorCount = operatorCount

        dfs(1, numbers[0])
        return answer
    }

    private fun dfs(index: Int, calcNum: Int) {
        if (numbers.size == index) {
            answer[0] = answer[0].coerceAtLeast(calcNum)
            answer[1] = answer[1].coerceAtMost(calcNum)
            return
        }

        for (i in 0 until 4) {
            if (operatorCount[i] == 0) continue

            operatorCount[i]--
            val num = when (i) {
                0 -> calcNum + numbers[index]
                1 -> calcNum - numbers[index]
                2 -> calcNum * numbers[index]
                3 -> calcNum / numbers[index]
                else -> error("")
            }
            dfs(index + 1, num)
            operatorCount[i]++
        }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val numbers = readLine()!!.split(" ")
        .map { it.toInt() }
        .toIntArray()
    val operatorCount = readLine()!!.split(" ")
        .map { it.toInt() }
        .toIntArray()

    Problem4888().solution(numbers, operatorCount).forEach(::println)
}
