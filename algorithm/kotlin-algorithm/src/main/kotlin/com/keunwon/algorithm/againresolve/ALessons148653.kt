package com.keunwon.algorithm.againresolve

class ALessons148653 {
    fun solution(storey: Int): Int {
        var num = storey
        var answer = 0

        while (num > 0) {
            val cur = num % 10
            var next = num / 10 % 10

            when {
                cur < 5 -> answer += cur
                cur > 5 -> {
                    answer += 10 - cur
                    num += 10
                }
                cur == 5 -> {
                    answer += 5
                    if (next >= 5) num += 10
                }
            }
            num /= 10
        }
        return answer
    }
}

fun main() {
    intArrayOf(16, 2554).forEach { storey ->
        ALessons148653().solution(storey).also(::println)
    }
}
