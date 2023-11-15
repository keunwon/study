package com.keunwon.algorithm.againresolve

import kotlin.math.abs

class ALessons67257 {
    private lateinit var operators: CharArray
    private lateinit var numbers: LongArray

    private var answer = 0L

    fun solution(expression: String): Long {
        this.operators = expression.replace("\\d".toRegex(), "").toCharArray()
        this.numbers = expression.split("[-+*]".toRegex())
            .map { it.toLong() }
            .toLongArray()
        backtracking(0, CharArray(3), BooleanArray(3))
        return answer
    }

    private fun backtracking(depth: Int, usedOperators: CharArray, visited: BooleanArray) {
        if (depth == usedOperators.size) {
            val tmpOperators = operators.toMutableList()
            val tmpNumbers = numbers.toMutableList()

            for (op in usedOperators) {
                var i = 0
                while (i < tmpOperators.size) {
                    if (op == tmpOperators[i]) {
                        val calcNum = calc(
                            tmpOperators.removeAt(i),
                            tmpNumbers.removeAt(i),
                            tmpNumbers.removeAt(i)
                        )
                        tmpNumbers.add(i, calcNum)
                    } else ++i
                }
            }
            answer = answer.coerceAtLeast(abs(tmpNumbers[0]))
            return
        }

        for ((i, op) in charArrayOf('+', '-', '*').withIndex()) {
            if (visited[i]) continue

            visited[i] = true
            usedOperators[depth] = op
            backtracking(depth + 1, usedOperators, visited)
            visited[i] = false
        }
    }

    private fun calc(operator: Char, a: Long, b: Long): Long = when (operator) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        else -> error("not found operator: $operator")
    }
}

fun main() {
    ALessons67257().solution("100-200*300-500+20").also(::println)
    ALessons67257().solution("50*6-3*2").also(::println)
}
