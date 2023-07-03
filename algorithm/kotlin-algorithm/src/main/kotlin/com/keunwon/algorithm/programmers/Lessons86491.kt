package com.keunwon.algorithm.programmers

/**
 * Title: 최소직사각형
 * Level: 1
 **/
class Lessons86491 {
    fun solution(sizes: Array<IntArray>): Int {
        val n = sizes.maxOf { size -> size.maxOf { it } }
        val m = sizes.maxOf { size -> size.minOf { it } }
        return n * m
    }
}
