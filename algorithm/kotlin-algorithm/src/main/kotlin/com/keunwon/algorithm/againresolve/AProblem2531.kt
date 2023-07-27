package com.keunwon.algorithm.againresolve

class AProblem2531 {
    fun solution(arr: IntArray, d: Int, k: Int, c: Int): Int {
        val eats = IntArray(d + 1)
        var answer = 0
        var count = 0

        for (i in 0 until k) {
            val num = arr[i]
            if (eats[num] == 0) count++
            eats[num]++
        }

        for (i in arr.indices) {
            if (answer <= count) {
                answer = count + if (eats[c] == 0) 1 else 0
            }

            val start = arr[i]
            eats[start]--
            if (eats[start] == 0) count--

            val end = arr[(k + i) % arr.size]
            if (eats[end] == 0) count++
            eats[end]++
        }
        return answer
    }
}

fun main() {
    val (n, d, k, c) = readLine()!!.split(" ").map { it.toInt() }
    val arr = IntArray(n) { readLine()!!.toInt() }

    AProblem2531().solution(arr, d, k, c).also { println(it) }
}
