package com.keunwon.algorithm.programmers

/**
 * Title: 문자열 내 마음대로 정렬하기
 * Level: 1
 **/
class Lessons12915 {
    fun solution(strings: Array<String>, n: Int): Array<String> {
        return strings.sortedWith(compareBy({ it[n] }, { it })).toTypedArray()
    }
}
