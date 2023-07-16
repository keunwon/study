package com.keunwon.algorithm.baekjoon

/**
 * Title: 0 만드리기
 * Level: 골드-5
 **/
class Problem7490 {
    private val answers = mutableListOf<String>()

    fun solution(n: Int): Array<String> {
        dfs(1, "1", n)
        return answers.sorted().toTypedArray()
    }

    private fun dfs(cur: Int, str: String, n: Int) {
        if (cur == n) {
            val expression = str.replace(" ", "")
            val result = calc(expression)
            if (result == 0) answers.add(str)
            return
        }

        charArrayOf('+', '-', ' ').forEach { op ->
            dfs(cur + 1, "$str$op${cur + 1}", n)
        }
    }

    private fun calc(expression: String): Int {
        val numbers = expression.split("[+-]".toRegex()).map { it.toInt() }
        val operators = expression.replace("[0-9]".toRegex(), "").toCharArray()

        var sum = numbers[0]
        for ((i, op) in operators.withIndex()) {
            val tmp = when (op) {
                '+' -> numbers[i + 1]
                '-' -> -numbers[i + 1]
                else -> error("")
            }
            sum += tmp
        }
        return sum
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val n = readLine()!!.toInt()
        Problem7490().solution(n).also { println(it.joinToString("\n")) }
        println()
    }
}
