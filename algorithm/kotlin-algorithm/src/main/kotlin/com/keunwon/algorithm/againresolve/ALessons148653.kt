package com.keunwon.algorithm.againresolve

class ALessons148653 {
    fun solution(storey: Int): Int {
        var target = storey
        var answer = 0

        while (target != 0) {
            val mod = target % 10
            when {
                mod < 5 -> answer += mod

                mod == 5 -> {
                    val next = target / 10 % 10
                    target += if (next >= 5) 10 else 0
                    answer += 5
                }

                mod > 5 -> {
                    target += 10
                    answer += 10 - mod
                }
            }
            target /= 10
        }
        return answer
    }
}

fun main() {
    ALessons148653().solution(16).also(::println)
    ALessons148653().solution(2554).also(::println)
    ALessons148653().solution(100).also(::println)
    ALessons148653().solution(55).also(::println)
    ALessons148653().solution(75).also(::println)
    ALessons148653().solution(45).also(::println)
    ALessons148653().solution(5).also(::println)
    ALessons148653().solution(555).also(::println)
    ALessons148653().solution(655).also(::println)
}
