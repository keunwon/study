package com.keunwon.algorithm.againresolve

class AProblem1522 {
    fun solution(word: String): Int {
        val aCount = word.count { it == 'a' }
        var answer = 1_001

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
    AProblem1522().solution(word).also { println(it) }
}
