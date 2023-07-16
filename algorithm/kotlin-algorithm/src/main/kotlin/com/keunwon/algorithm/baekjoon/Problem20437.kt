package com.keunwon.algorithm.baekjoon

/**
 * Title: 문자열 게임 2
 * Level: 골드-5
 **/
class Problem20437 {
    fun solution(w: String, k: Int): IntArray {
        if (k == 1) return intArrayOf(1, 1)

        val alphabets = ('a'..'z').associateWith { 0 }.toMutableMap().apply {
            w.forEach { this[it] = this.getOrDefault(it, 0) + 1 }
        }
        var min = Int.MAX_VALUE
        var max = 0

        for ((i, c) in w.withIndex()) {
            if (alphabets.getValue(c) < k) continue

            var count = 1
            for (j in i + 1 until w.length) {
                if (c == w[j]) count++
                if (k == count) {
                    min = min.coerceAtMost(j - i + 1)
                    max = max.coerceAtLeast(j - i + 1)
                    break
                }
            }
        }
        return if (min == Int.MAX_VALUE && max == 0) intArrayOf(-1) else intArrayOf(min, max)
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val w = readLine()!!
        val k = readLine()!!.toInt()

        Problem20437().solution(w, k).also { println(it.joinToString(" ")) }
    }
}
