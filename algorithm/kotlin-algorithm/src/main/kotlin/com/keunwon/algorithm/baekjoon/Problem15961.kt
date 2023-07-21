package com.keunwon.algorithm.baekjoon

/**
 * Title: 회전 초밥
 * Level: 골드-4
 **/
class Problem15961 {
    fun solution(d: Int, k: Int, c: Int, arr: IntArray): Int {
        val eats = IntArray(d + 1)
        var answer = 0
        var tmpCount = 0

        for (i in 0 until k) {
            val num = arr[i]
            if (eats[num] == 0) tmpCount++
            eats[num]++
        }

        for (i in arr.indices) {
            if (answer <= tmpCount) {
                answer = tmpCount + if (eats[c] == 0) 1 else 0
            }

            val prevIndex = arr[i]
            eats[prevIndex]--
            if (eats[prevIndex] == 0) tmpCount--

            val nextIndex = arr[(i + k) % arr.size]
            if (eats[nextIndex] == 0) tmpCount++
            eats[nextIndex]++
        }
        return answer
    }
}

fun main() {
    val (n, d, k, c) = readLine()!!.split(" ").map { it.toInt() }
    val arr = IntArray(n) { readLine()!!.toInt() }

    Problem15961().solution(d, k, c, arr).also { println(it) }
}
