package com.keunwon.algorithm.baekjoon

/**
 * Title: 벌집
 * Level: 브론즈-2
 **/
class Problem2292 {
    fun solution(n: Int): Int {
        if (n == 1) return 1

        var num = 1
        var answer = 1
        while (true) {
            num += 6 * answer
            answer++
            if (n <= num) break
        }
        return answer
    }
}

fun main() {
    val n = readLine()!!.toInt()
    Problem2292().solution(n).also { println(it) }
}
