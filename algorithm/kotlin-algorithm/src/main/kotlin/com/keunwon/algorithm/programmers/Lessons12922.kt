package com.keunwon.algorithm.programmers

/**
 * Title: 수박수박수박수박수박수?
 * Level: 1
 **/
class Lessons12922 {
    fun solution(n: Int): String {
        return CharArray(n) { if (it % 2 == 0) '수' else '박' }.joinToString("")
    }
}
