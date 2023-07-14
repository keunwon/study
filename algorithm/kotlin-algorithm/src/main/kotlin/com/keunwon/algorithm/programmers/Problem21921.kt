package com.keunwon.algorithm.programmers

/**
 * Title: 블로그
 * Level: 실버-3
 **/
class Problem21921 {
    fun solution(x: Int, blog: IntArray): String {
        val prefixSum = IntArray(blog.size + 1).apply {
            blog.indices.forEach { this[it + 1] = this[it] + blog[it] }
        }

        var max = Int.MIN_VALUE
        var count = 1
        for (i in 0 until prefixSum.size - x) {
            val num = prefixSum[i + x] - prefixSum[i]

            if (max == num) count++
            else if (max < num) {
                max = num
                count = 1
            }
        }
        return if (max == 0) "SAD" else "$max\n$count"
    }
}

fun main() {
    val (n, x) = readLine()!!.split(" ").map { it.toInt() }
    val blog = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem21921().solution(x, blog).also { println(it) }
}
