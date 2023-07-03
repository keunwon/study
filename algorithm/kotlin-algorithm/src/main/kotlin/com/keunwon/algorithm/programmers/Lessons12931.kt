package com.keunwon.algorithm.programmers

/**
 * Title: 자릿수 더하기
 * Level: 1
 */
class Lessons12931 {
    fun solution(n: Int): Int = "$n".sumOf { it.digitToInt() }
}
