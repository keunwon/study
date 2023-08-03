package com.keunwon.algorithm.baekjoon

/**
 * Title: 괄호 추가하기 2
 * Level: 골드-1
 **/
class Problem16638 {
    private lateinit var numbers: List<Int>
    private lateinit var operators: List<Char>
    private var answer = Int.MIN_VALUE

    fun solution(expression: String): Int {
        this.numbers = expression.split("[*+-]".toRegex()).map { it.toInt() }.toList()
        this.operators = expression.replace("[\\d]".toRegex(), "").toList()

        dfs(0, 0)
        return 0
    }

    private fun dfs(index: Int, sum: Int) {
        if (index >= operators.size) {
            return
        }

        val num1 = calc(numbers[index], numbers[index + 1], operators[index])
        dfs(index + 1, sum + num1)

        if (index + 1 < operators.size) {
            val num2 = calc(numbers[index + 1], numbers[index + 2], operators[index + 1])
            dfs(index + 2, calc(numbers[index], num2, operators[index]))
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
    Problem16638().solution(expression).also { println(it) }
}
