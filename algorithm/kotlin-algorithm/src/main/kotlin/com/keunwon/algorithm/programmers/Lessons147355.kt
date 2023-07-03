package com.keunwon.algorithm.programmers

/**
 * Title: 크기가 작은 부분 문자열
 * Level: 1
 **/
class Lessons147355 {
    fun solution(t: String, p: String): Int {
        return (0..t.length - p.length)
            .map { t.substring(it, it + p.length) }
            .count { it.toLong() <= p.toLong() }
    }
}
