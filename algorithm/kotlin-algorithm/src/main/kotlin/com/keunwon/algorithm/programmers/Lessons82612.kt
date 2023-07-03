package com.keunwon.algorithm.programmers

/**
 * Title: 부족한 금액 계산하기
 * Level: 1
 **/
class Lessons82612 {
    fun solution(price: Int, money: Int, count: Int): Long {
        val generate = generateSequence(price.toLong()) { it + price }
        val sum = generate.take(count).sum()
        return if (money >= sum) 0 else sum - money
    }
}
