package com.keunwon.algorithm.programmers

/**
 * Title: 튜플
 * Level: 2
 **/
class Lessons64065 {
    fun solution(s: String): IntArray {
        val arr = s.substring(2, s.length - 2)
            .split("},{")
            .sortedBy { it.length }

        val answer = mutableListOf<Int>()
        for (str in arr) {
            for (n in str.split(",").map(String::toInt)) {
                if (!answer.contains(n)) answer.add(n)
            }
        }
        return answer.toIntArray()
    }
}
