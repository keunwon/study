package com.keunwon.algorithm.againresolve

class AProblem20437 {
    fun solution(w: String, k: Int): IntArray {
        if (k == 1) return intArrayOf(1, 1)

        val alphabet = ('a'..'z').associateWith { key -> w.count { it == key } }
        val answer = intArrayOf(Int.MAX_VALUE, 0)

        for ((i, c) in w.withIndex()) {
            if (alphabet.getValue(c) < k) continue

            var count = 1
            for (j in i + 1 until w.length) {
                if (c == w[j]) count++
                if (k == count) {
                    answer[0] = answer[0].coerceAtMost(j - i + 1)
                    answer[1] = answer[1].coerceAtLeast(j - i + 1)
                    break
                }
            }
        }
        return if (answer.contentEquals(intArrayOf(Int.MAX_VALUE, 0))) intArrayOf(-1) else answer
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
