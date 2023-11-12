package com.keunwon.algorithm.againresolve

class ALessons12945 {
    fun solution(n: Int): Int {
        val arr = IntArray(n + 1).apply { this[1] = 1 }
        for (i in 2..n) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1234567
        }
        return arr[n]
    }
}

fun main() {
    ALessons12945().solution(5).also(::println)
}
