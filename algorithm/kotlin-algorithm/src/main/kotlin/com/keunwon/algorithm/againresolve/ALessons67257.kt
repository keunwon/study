package com.keunwon.algorithm.againresolve

import kotlin.math.abs

class ALessons67257 {
    private lateinit var numbers: List<Long>
    private lateinit var operators: List<Char>
    private var answer = 0L

    fun solution(expression: String): Long {
        this.numbers = expression.split("[-*+]".toRegex()).map { it.toLong() }
        this.operators = expression.replace("[\\d]".toRegex(), "").toList()

        permutation(0, CharArray(3), BooleanArray(3))
        return answer
    }

    private fun permutation(depth: Int, usedOperators: CharArray, visited: BooleanArray) {
        if (depth == usedOperators.size) {
            answer = answer.coerceAtLeast(calc(usedOperators))
            return
        }
        for ((i, op) in charArrayOf('+', '-', '*').withIndex()) {
            if (visited[i]) continue

            visited[i] = true
            usedOperators[depth] = op
            permutation(depth + 1, usedOperators, visited)
            visited[i] = false
        }
    }

    private fun calc(usedOperators: CharArray): Long {
        val numbers = this.numbers.toMutableList()
        val operators = this.operators.toMutableList()

        for (uop in usedOperators) {
            var j = 0

            while (j < operators.size) {
                if (uop != operators[j]) {
                    j++
                    continue
                }

                val (tmp1, tmp2) = LongArray(2) { numbers.removeAt(j) }
                val calcNum = when (operators.removeAt(j)) {
                    '+' -> tmp1 + tmp2
                    '-' -> tmp1 - tmp2
                    '*' -> tmp1 * tmp2
                    else -> error("")
                }
                numbers.add(j, calcNum)
            }
        }
        return abs(numbers.first())
    }
}

fun main() {
    arrayOf("100-200*300-500+20", "50*6-3*2").forEach { expression ->
        ALessons67257().solution(expression).also { println(it) }
    }
}
