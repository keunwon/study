package com.keunwon.algorithm.baekjoon

class Problem7490 {
    private val answer = mutableListOf<String>()

    fun solution(n: Int): Array<String> {
        dfs(1, "1", n)
        return answer.sorted().toTypedArray()
    }

    private fun dfs(cur: Int, str: String, n: Int) {
        if (cur == n) {
            val expression = str.replace(" ", "")
            val numbers = expression.split("[+-]".toRegex()).map { it.toInt() }
            val operators = expression.replace("\\d".toRegex(), "")
            var calcNum = numbers[0]

            for ((i, op) in operators.withIndex()) {
                if (op == '+') calcNum += numbers[i + 1]
                else if (op == '-') calcNum -= numbers[i + 1]
            }

            if (calcNum == 0) answer.add(str)
            return
        }

        for (op in charArrayOf('+', '-', ' ')) {
            dfs(cur + 1, "${str}${op}${cur + 1}", n)
        }
    }
}

fun main() {
    repeat(readln().toInt()) {
        val n = readln().toInt()

        Problem7490().solution(n).also { println(it.joinToString(System.lineSeparator())) }
        println()
    }
}
