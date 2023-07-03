package com.keunwon.algorithm.programmers

/**
 * Title: 하샤드 수
 * Level: 1
 **/
class Lessons12947 {
    fun solution(x: Int): Boolean = "$x".map { it.digitToInt() }.sum().let { x % it == 0 }
}
