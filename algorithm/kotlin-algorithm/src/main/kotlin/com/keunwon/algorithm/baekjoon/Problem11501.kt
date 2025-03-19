package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 주식
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/11501">주식 (실버-2)</a>
 **/
class Problem11501 {
    fun solution(prices: List<Long>): Long {
        var result = 0L
        var max = 0L

        for (i in prices.lastIndex downTo 0) {
            if (max < prices[i]) max = prices[i] else result += max - prices[i]
        }
        return result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()
    repeat(t) {
        val n = br.readLine().toInt()
        val prices = br.readLine().split(" ").map { it.toLong() }

        Problem11501().solution(prices).also {
            bw.write("$it")
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    br.close()
}
