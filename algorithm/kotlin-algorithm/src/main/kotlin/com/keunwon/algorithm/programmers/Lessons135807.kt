package com.keunwon.algorithm.programmers

/**
 * Title: 숫자 카드 나누기
 * Level: 2
 **/
class Lessons135807 {
    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        var gcd1 = arrayA[0]
        var gcd2 = arrayB[0]

        for (i in 1 until arrayA.size) {
            gcd1 = gcd(gcd1, arrayA[i])
            gcd2 = gcd(gcd2, arrayB[i])
        }

        var answer = 0
        if (check(arrayA, gcd2)) answer = answer.coerceAtLeast(gcd2)
        if (check(arrayB, gcd1)) answer = answer.coerceAtLeast(gcd1)
        return answer
    }

    private fun check(arrayA: IntArray, gcd2: Int): Boolean = arrayA.all { it % gcd2 != 0 }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }
}
