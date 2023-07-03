package com.keunwon.algorithm.programmers

/**
 * Title: 정수 내림차순으로 배치하기
 * Level: 1
 **/
class Lessons12933 {
    fun solution(n: Long): Long = "$n".map { it.digitToInt() }.sortedDescending().joinToString("").toLong()
}
