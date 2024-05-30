package com.keunwon.algorithm.programmers

class Lesson60057 {
    fun solution(s: String): Int {
        var min = s.length

        for (size in 1..s.length / 2) {
            val words = s.chunked(size)
            var count = 1
            var comb = words[0]
            val sb = StringBuilder()

            for (i in 1 until words.size) {
                val word = words[i]

                if (comb == word) ++count
                else {
                    sb.append(if (count == 1) comb else "$count$comb")
                    comb = word
                    count = 1
                }
            }
            if (count == 1) sb.append(comb) else sb.append("$count$comb")

            if (min > sb.length) min = sb.length
        }
        return min
    }
}
