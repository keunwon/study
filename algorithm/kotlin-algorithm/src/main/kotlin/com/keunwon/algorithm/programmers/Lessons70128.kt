package com.keunwon.algorithm.programmers

/**
 * Title: 내적
 * Level: 1
 **/
class Lessons70128 {
    fun solution(a: IntArray, b: IntArray): Int = a.zip(b).sumOf { it.first * it.second }
}
