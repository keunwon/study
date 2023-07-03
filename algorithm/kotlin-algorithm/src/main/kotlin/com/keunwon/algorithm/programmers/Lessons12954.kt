package com.keunwon.algorithm.programmers

/**
 * Title: x만큼 간격이 있는 n개의 숫자
 * Level: 1
 **/
class Lessons12954 {
    fun solution(x: Int, n: Int): LongArray = LongArray(n) { x.toLong() * (it + 1) }
}
