package com.keunwon.algorithm.programmers

/**
 * Title: 약수의 개수와 덧셈
 * Level: 1
 **/
class Lessons77884 {
    fun solution(left: Int, right: Int): Int {
        return (left..right).sumOf { num ->
            val count = (1..num).count { num % it == 0 }
            if (count % 2 == 0) num else -num
        }
    }
}
