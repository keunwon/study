package algorithm.programmers

import kotlin.math.pow

class Lesson181930 {
    fun solution(a: Int, b: Int, c: Int): Int {
        val set = setOf(a, b, c)
        return when (set.size) {
            3 -> a + b + c
            2 -> (a + b + c) * (a * a + b * b + c * c)
            1 -> (a + b + c) * (pow(a, 2) + pow(b, 2) + pow(c, 2)) * (pow(a, 3) + pow(b, 3) + pow(c, 3))
            else -> -1
        }
    }

    private fun pow(base: Int, n: Int): Int = base.toDouble().pow(n).toInt()
}
