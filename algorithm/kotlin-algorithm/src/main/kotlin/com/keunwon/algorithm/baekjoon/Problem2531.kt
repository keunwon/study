package com.keunwon.algorithm.baekjoon

class Problem2531 {
    fun solution(d: Int, k: Int, c: Int, arr: IntArray): Int {
        val sushi = IntArray(d + 1)
        var count = 0

        for (i in 0 until k) {
            val num = arr[i]
            if (sushi[num] == 0) ++count
            ++sushi[num]
        }

        var answer = count
        for (i in arr.indices) {
            if (answer <= count) {
                answer = if (sushi[c] == 0) count + 1 else count
            }

            val sIndex = arr[i]
            val eIndex = arr[(i + k) % arr.size]

            if (--sushi[sIndex] == 0) --count
            if (++sushi[eIndex] == 1) ++count
        }
        return answer
    }
}

fun main() {
    val (n, d, k, c) = readln().split(" ").map { it.toInt() }
    val arr = IntArray(n) { readln().toInt() }
    println(Problem2531().solution(d, k, c, arr))
}
