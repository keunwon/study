package com.keunwon.algorithm.againresolve

class ALessons12904 {
    fun solution(s: String): Int {
        for (size in s.length downTo 1) {
            var i = 0
            while (i + size <= s.length) {
                val text = s.substring(i, i + size)
                if (check(text)) return size
                ++i
            }
        }
        return 0
    }

    private fun check(text: String): Boolean {
        var sIndex = 0
        var eIndex = text.lastIndex

        while (sIndex <= eIndex) {
            if (text[sIndex++] != text[eIndex--]) return false
        }
        return true
    }
}

fun main() {
    ALessons12904().solution("abcdcba").also(::println)
    ALessons12904().solution("abacde").also(::println)
}
