package com.keunwon.algorithm.againresolve

class ALessons138475 {
    fun solution(e: Int, starts: IntArray): IntArray {
        val dp = Array(e + 1) { Node(it, 0) }.apply {
            for (i in 1..e) {
                for (j in i..e step i) {
                    this[j].count++
                }
            }
        }.sortedWith(compareBy({ -it.count }, { it.n }))

        return starts.map { start ->
            dp.first { start <= it.n }.n
        }.toIntArray()
    }

    private data class Node(val n: Int, var count: Int)
}

fun main() {
    val e = 8
    val starts = intArrayOf(1, 3, 7)
    ALessons138475().solution(e, starts).also { println(it.contentToString()) }
}
