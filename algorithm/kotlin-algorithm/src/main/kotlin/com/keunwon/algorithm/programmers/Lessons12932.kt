package com.keunwon.algorithm.programmers

/**
 * Title: 자연수 뒤집어 배열로 만들기
 * Level: 1
 **/
class Lessons12932 {
    fun solution(n: Long): IntArray = "$n".reversed().map { it.digitToInt() }.toIntArray()
}
