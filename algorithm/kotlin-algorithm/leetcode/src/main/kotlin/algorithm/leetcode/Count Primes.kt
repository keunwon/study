package algorithm.leetcode

import kotlin.math.sqrt

class `Count Primes` {
    fun countPrimes(n: Int): Int {
        val prime = BooleanArray(n + 1) { true }

        if (n > 1) prime[2] = true
        for (i in 2..sqrt(n.toDouble()).toInt()) {
            for (j in i + i until prime.size step i) {
                prime[j] = false
            }
        }
        return (2..n).count { prime[it] }
    }
}

fun main() {
    `Count Primes`().countPrimes(2).also { println(it) }
}
