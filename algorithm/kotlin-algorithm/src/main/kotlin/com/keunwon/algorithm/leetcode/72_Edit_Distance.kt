package com.keunwon.algorithm.leetcode

class `72_Edit_Distance` {
    fun minDistance(word1: String, word2: String): Int {
        val dp = Array(word1.length + 1) { IntArray(word2.length + 1) { 1e9.toInt() } }.apply { this[0][0] = 0 }

        for (i in 1 until dp.size) {
            dp[i][0] = i
        }

        for (i in 1 until dp[0].size) {
            dp[0][i] = i
        }

        for (i in 1 until dp.size) {
            for (j in 1 until dp[0].size) {
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
                }
            }
        }
        return dp.last().last()
    }
}

fun main() {
    `72_Edit_Distance`().minDistance(word1 = "horse", word2 = "ros")
        .also { println(it) } // 3

    `72_Edit_Distance`().minDistance(word1 = "intention", word2 = "execution")
        .also { println(it) } // 5
}
