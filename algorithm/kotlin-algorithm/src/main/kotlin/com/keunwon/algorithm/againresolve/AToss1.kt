package com.keunwon.algorithm.againresolve

class AToss1 {
    fun solution(s: String): Int {
        var answer = -1
        for (i in 0 until s.length - 3) {
            if (s[i] == s[i + 1] && s[i + 1] == s[i + 2]) {
                answer = answer.coerceAtLeast(s.substring(i, i + 3).toInt())
            }
        }
        return answer
    }
}

fun main() {
    AToss1().solution("12223").also { println(it) }
}
