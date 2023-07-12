package com.keunwon.algorithm.programmers

/**
 * Title: 가장 긴 팰린드롬
 * Level: 2
 **/
class Lessons12904 {
    fun solution(s: String): Int {
        for (size in s.length downTo 1) {
            var j = 0
            while (j + size <= s.length) {
                if (check(j, j + size - 1, s)) return size
                j++
            }
        }
        return 0
    }

    private fun check(start: Int, end: Int, word: String): Boolean {
        var s = start
        var e = end

        while (s <= e) {
            if (word[s++] != word[e--]) return false
        }
        return true
    }
}
