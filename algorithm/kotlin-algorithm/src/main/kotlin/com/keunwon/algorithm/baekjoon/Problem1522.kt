package com.keunwon.algorithm.baekjoon

/**
 * Title: 문자열 교환
 * Level: 실버-1
 **/
class Problem1522 {
    fun solution(word: String): Int {
        val aCount = word.count { it == 'a' }
        var answer = Int.MAX_VALUE

        for (i in word.indices) {
            var bCount = 0
            for (j in i until i + aCount) {
                if (word[j % word.length] == 'b') bCount++
            }
            answer = answer.coerceAtMost(bCount)
        }
        return answer
    }
}

fun main() {
    val word = readLine()!!
    Problem1522().solution(word).also { println(it) }
}
