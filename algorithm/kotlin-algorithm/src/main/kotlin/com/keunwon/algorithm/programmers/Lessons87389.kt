package com.keunwon.algorithm.programmers

/**
 * Title: 나머지가 1이 되는 수 찾기
 * Level: 1
 **/
class Lessons87389 {
    fun solution(n: Int): Int = (1..n).first { n % it == 1 }
}
