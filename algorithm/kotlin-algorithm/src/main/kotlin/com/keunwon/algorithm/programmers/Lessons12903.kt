package com.keunwon.algorithm.programmers

/**
 * Title: 가운데 글자 가져오기
 * Level: 1
 **/
class Lessons12903 {
    fun solution(s: String): String {
        val mid = s.length / 2
        return if (s.length % 2 == 1) "${s[mid]}"
        else "${s[mid - 1]}${s[mid]}"
    }
}
