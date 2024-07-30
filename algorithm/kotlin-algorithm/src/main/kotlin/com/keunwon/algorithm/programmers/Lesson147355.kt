package com.keunwon.algorithm.programmers

class Lesson147355 {
    fun solution(t: String, p: String): Int {
        val numbers = mutableListOf<String>()

        for (i in 0..t.length - p.length) {
            numbers.add(t.substring(i, i + p.length))
        }
        return numbers.count { it <= p }
    }
}
