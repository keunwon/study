package algorithm.programmers

import kotlin.math.abs
import kotlin.math.max

class Lesson67257 {
    private lateinit var numbers: List<Long>
    private lateinit var operators: List<Char>

    private var answer = 0L

    fun solution(expression: String): Long {
        this.numbers = expression.split("[-+*]".toRegex()).map { it.toLong() }
        this.operators = expression.replace("\\d".toRegex(), "").toList()

        dfs(0, CharArray(3), BooleanArray(3))
        return answer
    }

    private fun dfs(depth: Int, usedOperators: CharArray, visited: BooleanArray) {
        if (depth == visited.size) {
            val tmpNumbers = numbers.toMutableList()
            val tmpOperators = operators.toMutableList()

            for (op in usedOperators) {
                var i = 0
                while (i < tmpOperators.size) {
                    if (op == tmpOperators[i]) {
                        val calcNum = calculate(
                            tmpNumbers.removeAt(i),
                            tmpNumbers.removeAt(i),
                            tmpOperators.removeAt(i)
                        )
                        tmpNumbers.add(i, calcNum)
                    } else {
                        ++i
                    }
                }
            }
            answer = max(answer, abs(tmpNumbers[0]))
            return
        }

        for ((i, op) in charArrayOf('+', '-', '*').withIndex()) {
            if (!visited[i]) {
                visited[i] = true
                usedOperators[depth] = op
                dfs(depth + 1, usedOperators, visited)
                visited[i] = false
            }
        }
    }

    private fun calculate(a: Long, b: Long, operator: Char): Long = when (operator) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        else -> error("not support operator: $operator")
    }
}
