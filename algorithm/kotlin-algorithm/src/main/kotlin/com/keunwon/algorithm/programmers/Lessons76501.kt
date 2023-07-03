package com.keunwon.algorithm.programmers

/**
 * Title: 음양 더하기
 * Level: 1
 **/
class Lessons76501 {
    fun solution(absolutes: IntArray, signs: BooleanArray): Int {
        return absolutes.mapIndexed { index, num -> if (signs[index]) num else -num }.sum()
    }
}
