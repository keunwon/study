package com.keunwon.algorithm.againresolve

class ALessons135807 {
    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        var gcd1 = arrayA[0]
        var gcd2 = arrayB[0]

        for (i in 1 until arrayA.size) {
            gcd1 = gcd(gcd1, arrayA[i])
            gcd2 = gcd(gcd2, arrayB[i])
        }

        var answer = 0
        if (check(gcd1, arrayB)) answer = gcd1
        if (check(gcd2, arrayA)) answer = answer.coerceAtLeast(gcd2)
        return answer
    }

    private fun check(gcd: Int, arr: IntArray): Boolean {
        return arr.all { it % gcd != 0 }
    }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }
}

fun main() {
    val arrayA = intArrayOf(10, 20)
    val arrayB = intArrayOf(5, 17)

    ALessons135807().solution(arrayA, arrayB).also { println(it) }
}
