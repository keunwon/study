package com.keunwon.algorithm.programmers

/**
 * Title: 이상한 문자 만들기
 * Level: 1
 **/
class Lessons12930 {
    fun solution(s: String): String {
        return s.split(" ").joinToString(" ") { str ->
            str.lowercase().mapIndexed { index, c -> if (index % 2 == 0) c.uppercase() else c }.joinToString("")
        }
    }
}
