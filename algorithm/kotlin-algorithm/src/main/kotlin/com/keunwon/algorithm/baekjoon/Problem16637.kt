package com.keunwon.algorithm.baekjoon

/**
 * Title: 괄호 추가히기
 * Level: 골드-3
 **/
class Problem16637 {
    private lateinit var numbers: List<Int>
    private lateinit var operators: List<Char>
    private var answer = Int.MIN_VALUE

    fun solution(expression: String): Int {
        this.numbers = expression.split("[*+-]".toRegex()).map { it.toInt() }
        this.operators = expression.replace("\\d".toRegex(), "").toList()

        dfs(0, numbers[0])
        return answer
    }

    private fun dfs(index: Int, sum: Int) {
        if (index >= operators.size) {
            answer = answer.coerceAtLeast(sum)
            return
        }

        val num1 = calc(sum, numbers[index + 1], operators[index])
        dfs(index + 1, num1)

        if (index + 1 < operators.size) {
            val num2 = calc(numbers[index + 1], numbers[index + 2], operators[index + 1])
            dfs(index + 2, calc(sum, num2, operators[index]))
        }
    }

    private fun calc(a: Int, b: Int, operator: Char): Int = when (operator) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        else -> error("")
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val expression = readLine()!!

    Problem16637().solution(expression).also { println(it) }
}
