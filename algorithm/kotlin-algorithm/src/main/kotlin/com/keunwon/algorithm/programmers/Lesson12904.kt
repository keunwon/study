package com.keunwon.algorithm.programmers

class Lesson12904 {
    fun solution(s: String): Int {
        for (size in s.length downTo 1) {
            for (i in 0..s.length - size) {
                if (check(s, i, i + size - 1)) return size
            }
        }
        return 1
    }

    private fun check(s: String, startIndex: Int, endIndex: Int): Boolean {
        var left = startIndex
        var right = endIndex

        while (left <= right) {
            if (s[left++] != s[right--]) return false
        }
        return true
    }
}
