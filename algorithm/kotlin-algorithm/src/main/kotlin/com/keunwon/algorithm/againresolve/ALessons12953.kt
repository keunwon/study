package com.keunwon.algorithm.againresolve

class ALessons12953 {
    fun solution(arr: IntArray): Int = arr.fold(1) { acc, num ->
        acc * num / gcd(acc, num)
    }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }
}

fun main() {
    ALessons12953().solution(intArrayOf(2, 6, 8, 14)).also(::println)
}
