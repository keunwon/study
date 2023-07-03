package com.keunwon.algorithm.programmers

/**
 * Title: 약수의 합
 * Level: 1
 **/
class Lessons12928 {
    fun solution(n: Int): Int = (1..n).filter { n % it == 0 }.sum()
}
