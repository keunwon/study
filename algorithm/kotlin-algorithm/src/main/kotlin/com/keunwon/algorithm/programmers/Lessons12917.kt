package com.keunwon.algorithm.programmers

/**
 * Title: 문자열 내림차순으로 배치하기
 * Level: 1
 **/
class Lessons12917 {
    fun solution(s: String): String = s.toCharArray().sortedArrayDescending().joinToString("")
}
