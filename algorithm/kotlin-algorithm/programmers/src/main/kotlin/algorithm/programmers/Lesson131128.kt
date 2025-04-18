package algorithm.programmers

import kotlin.math.min

class Lesson131128 {
    fun solution(X: String, Y: String): String {
        val countMap1 = X.groupingBy { it.digitToInt() }.eachCount()
        val countMap2 = Y.groupingBy { it.digitToInt() }.eachCount()
        val numbers = IntArray(10)

        for (i in 0..9) {
            val min = min(countMap1.getOrDefault(i, 0), countMap2.getOrDefault(i, 0))
            numbers[i] = min
        }

        val result = StringBuilder()
        for (i in 9 downTo 0) {
            val count = numbers[i]
            if (count > 0) result.append("$i".repeat(numbers[i]))
        }
        return when {
            result.startsWith("0") -> "0"
            result.isBlank() -> "-1"
            else -> result.toString()
        }
    }
}
