package com.keunwon.algorithm.programmers

/**
 * Title: 없는 숫자 더하기
 * Level: 1
 **/
class Lessons86051 {
    fun solution(numbers: IntArray): Int = (0..9).sum() - numbers.sum()
}
