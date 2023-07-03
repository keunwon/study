package com.keunwon.algorithm.programmers

/**
 * Title: JadenCase 문자열 만들기
 * Level: 2
 **/
class Lessons12951 {
    fun solution(s: String): String {
        return s.lowercase().split(" ").joinToString(" ") {
            if (it == "") ""
            else it.first().uppercase() + it.substring(1)
        }
    }
}
