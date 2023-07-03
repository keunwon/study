package com.keunwon.algorithm.programmers

/**
 * Title: 문자열 다루기 기본
 * Level: 1
 **/
class Lessons12918 {
    fun solution(s: String): Boolean = """^\d{4}$|^\d{6}$""".toRegex().matches(s)
}
