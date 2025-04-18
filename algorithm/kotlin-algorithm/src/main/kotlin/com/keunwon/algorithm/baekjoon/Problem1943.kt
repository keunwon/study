package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 동전 분배
 * 난이도: 골드-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1943">동전 분배 (골드-2)</a>
 **/
class Problem1943 {
    fun solution(coins: Map<Int, Int>): Int {
        val total = coins.entries.sumOf { it.key * it.value }
            .takeIf { it % 2 == 0 }
            ?: return 0
        val target = total / 2
        val dp = BooleanArray(50_001).apply { this[0] = true }

        loop@ for ((price, quantity) in coins) {
            for (num in target downTo 0) {
                if (!dp[num]) continue
                for (q in 1..quantity) {
                    val index = price * q + num
                    if (index < dp.size) {
                        dp[index] = true
                        if (index == target) break@loop
                    } else {
                        break
                    }
                }
            }
        }
        return if (dp[target]) 1 else 0
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    for (i in 0 until 3) {
        val n = br.readLine().toInt()
        val coins = HashMap<Int, Int>(n)

        for (j in 0 until n) {
            val (coin, quantity) = br.readLine().split(" ").map { it.toInt() }
            coins[coin] = quantity
        }

        Problem1943().solution(coins).also {
            bw.write("$it")
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    br.close()
}
