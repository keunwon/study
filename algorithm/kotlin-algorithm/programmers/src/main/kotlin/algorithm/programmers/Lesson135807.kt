package algorithm.programmers

import kotlin.math.max

class Lesson135807 {
    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        val gcd1 = arrayA.reduce { acc, n -> gcd(acc, n) }
        val gcd2 = arrayB.reduce { acc, n -> gcd(acc, n) }
        var answer = Int.MIN_VALUE

        if (arrayB.none { it % gcd1 == 0 }) answer = max(answer, gcd1)
        if (arrayA.none { it % gcd2 == 0 }) answer = max(answer, gcd2)
        return if (answer == Int.MIN_VALUE) 0 else answer
    }

    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}
