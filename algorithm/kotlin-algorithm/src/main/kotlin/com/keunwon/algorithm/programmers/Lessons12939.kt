package com.keunwon.algorithm.programmers

/**
 * Title: 최댓값과 최솟값
 * Level: 2
 **/
class Lessons12939 {
    fun solution(s: String): String {
        return s.split(" ")
            .map { it.toInt() }
            .let { list -> "${list.minOf { it }} ${list.maxOf { it }}" }
    }
}
