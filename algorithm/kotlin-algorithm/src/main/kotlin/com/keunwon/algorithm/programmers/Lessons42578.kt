package com.keunwon.algorithm.programmers

/**
 * Title: 의상
 * Level: 1
 **/
class Lessons42578 {
    fun solution(clothes: Array<Array<String>>): Int {
        return clothes.groupBy { it[1] }.values
            .map { it.size }
            .fold(1) { acc, n -> acc * (n + 1) } - 1
    }
}
