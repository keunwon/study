package com.keunwon.algorithm.programmers

/**
 * Title: 문자열 압축
 * Level: 2
 **/
class Lessons60057 {
    fun solution(s: String): Int {
        var answer = s.length

        for (size in 1..s.length / 2) {
            val sb = StringBuilder()
            val words = s.chunked(size)
            var comb = words[0]
            var count = 1

            for (i in 1..words.size) {
                val word = if (i != words.size) words[i] else ""

                if (comb == word) {
                    count++
                    continue
                }

                sb.append(if (count > 1) "$count$comb" else comb)
                count = 1
                comb = word
            }
            answer = answer.coerceAtMost(sb.length)
        }
        return answer
    }
}
