package com.keunwon.algorithm.againresolve

class ALessons138476 {
    fun solution(k: Int, tangerine: IntArray): Int {
        val list = tangerine.groupBy { it }
            .map { it.value.size }
            .sortedDescending()
        var sum = 0
        var answer = 0

        for (n in list) {
            if (k <= sum) break
            sum += n
            ++answer
        }
        return answer
    }
}

fun main() {
    ALessons138476().solution(
        6,
        intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)
    ).also(::println)
}
