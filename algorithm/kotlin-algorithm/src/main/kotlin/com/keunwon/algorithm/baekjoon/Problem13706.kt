package com.keunwon.algorithm.baekjoon

import java.math.BigInteger

/**
 * Title: 제곱근
 * Level: 실버-4
 **/
class Problem13706 {
    fun solution(bi: BigInteger): BigInteger {
        var left = BigInteger.ONE
        var right = bi

        while (left <= right) {
            val mid = (left + right) / BigInteger("2")
            val double = mid.pow(2)

            if (bi <= double) right = mid - BigInteger("1")
            else left = mid + BigInteger("1")
        }
        return left
    }
}

fun main() {
    val bi = readLine()!!.toBigInteger()
    Problem13706().solution(bi).also { println(it) }
}
