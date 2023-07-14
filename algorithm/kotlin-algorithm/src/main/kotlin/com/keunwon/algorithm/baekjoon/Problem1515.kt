package com.keunwon.algorithm.baekjoon

/**
 * Title: 수 이어 쓰기
 * Level: 실버-3
 **/
class Problem1515 {
    fun solution(n: String): Int {
        var num = 0
        var curIdx = 0

        while (curIdx < 3_000) {
            num++

            for (w in num.toString()) {
                if (n[curIdx] == w) curIdx++
                if (n.length == curIdx) return num
            }
        }
        return -1
    }
}

fun main() {
    val n = readLine()!!
    Problem1515().solution(n).also { println(it) }
}
