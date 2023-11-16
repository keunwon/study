package com.keunwon.algorithm.againresolve

class ALessons60057 {
    fun solution(s: String): Int {
        var answer = s.length

        for (size in 1..s.length / 2) {
            val sb = StringBuilder()
            val words = s.chunked(size)
            var comb = words[0]
            var count = 1

            for (i in 1..words.size) {
                val word = if (words.size == i) "" else words[i]

                if (comb == word) {
                    ++count
                    continue
                }
                sb.append(if (count > 1) "$count$comb" else comb)
                comb = word
                count = 1
            }
            answer = answer.coerceAtMost(sb.length)
        }
        return answer
    }
}

fun main() {
    arrayOf(
        "aabbaccc",
        "ababcdcdababcdcd",
        "abcabcdede",
        "abcabcabcabcdededededede",
        "xababcdcdababcdcd"
    ).forEach { text -> ALessons60057().solution(text).also(::println) }
}
