package com.keunwon.algorithm.programmers

/**
 * Title: 두 정수 사이의 합
 * Level: 1
 **/
class Lessons12912 {
    fun solution(a: Int, b: Int): Long {
        return if (a < b) (a.toLong()..b).sum() else (b.toLong()..a).sum()
    }
}
