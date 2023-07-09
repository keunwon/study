package com.keunwon.algorithm.programmers

/**
 * Title: 마법의 엘리베이터
 * Level: 2
 **/
class Lessons148653 {
    fun solution(storey: Int): Int {
        var target = storey
        var answer = 0

        while (target != 0) {
            val mod = target % 10

            when {
                mod > 5 -> {
                    target += 10
                    answer += 10 - mod
                }

                mod == 5 -> {
                    val next = target / 10 % 10
                    target += if (5 <= next) 10 else 0
                    answer += 5
                }

                mod < 5 -> answer += mod
            }
            target /= 10
        }
        return answer
    }
}
