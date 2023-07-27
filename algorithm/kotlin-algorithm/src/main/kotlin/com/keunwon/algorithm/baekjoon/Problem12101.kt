package com.keunwon.algorithm.baekjoon

/**
 * Title: 1, 2, 3 더하기 2
 * Level: 실버-1
 **/
class Problem12101 {
    fun solution(n: Int, k: Int): String {
        val dp = Array(n + 3) { mutableListOf<String>() }.apply {
            this[1].add("1")
            this[2].add("1+1")
            this[2].add("2")
            this[3].add("1+1+1")
            this[3].add("1+2")
            this[3].add("2+1")
            this[3].add("3")
        }

        for (i in 4..n) {
            for (j in 1..3) {
                for (str in dp[i - j]) {
                    dp[i].add("$str+$j")
                }
            }
        }
        return dp[n].sorted().getOrNull(k - 1) ?: "-1"
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    Problem12101().solution(n, k).also { println(it) }
}
