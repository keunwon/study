package algorithm.programmers

import java.math.BigInteger

class Lesson120840 {
    fun solution(balls: Int, share: Int): Int {
        val result = f(balls) / (f(balls - share) * f(share))
        return result.intValueExact()
    }

    private fun f(end: Int): BigInteger {
        return (2..end).fold(BigInteger.ONE) { acc, n -> acc * BigInteger.valueOf(n.toLong()) }
    }
}
