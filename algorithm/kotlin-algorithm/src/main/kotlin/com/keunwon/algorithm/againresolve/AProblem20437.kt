package com.keunwon.algorithm.againresolve

class AProblem20437 {
    fun solution(w: String, k: Int): IntArray {
        if (k == 1) return intArrayOf(1, 1)

        val countMap = w.groupingBy { it }.eachCount().toMutableMap()
        val answer = intArrayOf(Int.MAX_VALUE, 0)

        for ((i, c) in w.withIndex()) {
            if (countMap.getValue(c) < k) continue

            var count = 1
            for (j in i + 1 until w.length) {
                if (c == w[j]) count++
                if (k == count) {
                    val length = j - i + 1
                    answer[0] = answer[0].coerceAtMost(length)
                    answer[1] = answer[1].coerceAtLeast(length)
                    break
                }
            }
        }
        return if (answer[0] == Int.MAX_VALUE && answer[1] == 0) intArrayOf(-1) else answer
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val w = readLine()!!
        val k = readLine()!!.toInt()

        AProblem20437().solution(w, k).also { println(it.joinToString(" ")) }
    }
}
