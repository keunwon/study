package com.keunwon.algorithm.programmers

import kotlin.math.abs

/**
 * Title: 수식 최대화
 * Level: 2
 **/
class Lessons67257 {
    private lateinit var numbers: LongArray
    private lateinit var operators: CharArray

    private var answer = 0L

    fun solution(expression: String): Long {
        numbers = expression.split("[-+*]".toRegex()).map(String::toLong).toLongArray()
        operators = expression.replace("\\d".toRegex(), "").toCharArray()

        dfs(0, CharArray(3), BooleanArray(3))
        return answer
    }

    private fun dfs(depth: Int, usedOperators: CharArray, visited: BooleanArray) {
        if (depth == 3) {
            val tmpNumbers = numbers.toMutableList()
            val tmpOperators = operators.toMutableList()

            for (op in usedOperators) {
                var i = 0
                while (i < tmpOperators.size) {
                    if (op == tmpOperators[i]) {
                        val num = calc(
                            tmpNumbers.removeAt(i),
                            tmpNumbers.removeAt(i),
                            tmpOperators.removeAt(i),
                        )
                        tmpNumbers.add(i, num)
                    } else i++
                }
            }
            answer = answer.coerceAtLeast(abs(tmpNumbers.first()))
            return
        }

        for ((i, op) in charArrayOf('+', '-', '*').withIndex()) {
            if (visited[i]) continue

            visited[i] = true
            usedOperators[depth] = op
            dfs(depth + 1, usedOperators, visited)
            visited[i] = false
        }
    }

    private fun calc(num1: Long, num2: Long, operator: Char): Long = when (operator) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        '*' -> num1 * num2
        else -> error("")
    }
}
