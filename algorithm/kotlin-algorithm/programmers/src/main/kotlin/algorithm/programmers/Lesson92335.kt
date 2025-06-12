package algorithm.programmers

import kotlin.math.sqrt

class Lesson92335 {
    fun solution(n: Int, k: Int): Int {
        val arr = n.toString(k).split("0").filter { it.isNotBlank() }
        return arr.count { it.toLong().isPrime() }
    }

    private fun Long.isPrime(): Boolean {
        if (this < 2) return false
        val end = sqrt(this.toDouble()).toInt()
        return (2..end).none { this % it == 0L }
    }
}
