package com.keunwon.algorithm.againresolve

class AProblem14888 {
    private lateinit var numbers: IntArray
    private lateinit var operators: IntArray

    private val answer = intArrayOf(Int.MIN_VALUE, Int.MAX_VALUE)

    fun solution(numbers: IntArray, operators: IntArray): IntArray {
        this.numbers = numbers
        this.operators = operators

        dfs(1, numbers[0])
        return answer
    }

    private fun dfs(index: Int, sum: Int) {
        if (index == numbers.size) {
            answer[0] = answer[0].coerceAtLeast(sum)
            answer[1] = answer[1].coerceAtMost(sum)
            return
        }

        for (i in operators.indices) {
            if (operators[i] == 0) continue

            operators[i]--

            val calcNum = when (i) {
                0 -> sum + numbers[index]
                1 -> sum - numbers[index]
                2 -> sum * numbers[index]
                3 -> sum / numbers[index]
                else -> error("")
            }
            dfs(index + 1, calcNum)

            operators[i]++
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val n = readLine().toInt()
    val numbers = readLine().split(" ").map { it.toInt() }.toIntArray()
    val operators = readLine().split(" ").map { it.toInt() }.toIntArray()

    AProblem14888().solution(numbers, operators).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}
