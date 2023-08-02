package com.keunwon.algorithm.againresolve

class ALessons12904 {
    fun solution(s: String): Int {
        for (size in s.length downTo 1) {
            var start = 0

            while (start + size <= s.length) {
                val valid = check(start, start + size - 1, s)
                if (valid) return size
                start++
            }
        }
        return 1
    }

    private fun check(startIndex: Int, endIndex: Int, word: String): Boolean {
        var s = startIndex
        var e = endIndex
        while (s <= e) {
            if (word[s++] != word[e--]) return false
        }
        return true
    }
}

fun main() {
    arrayOf("abcdcba", "abacde").forEach { s ->
        ALessons12904().solution(s).also { println(it) }
    }
}
