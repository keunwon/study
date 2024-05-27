package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem10431 {
    fun solution(numbers: IntArray): Int {
        var answer = 0

        for (i in 1 until numbers.size) {
            for (j in i - 1 downTo 0) {
                if (numbers[i] < numbers[j]) ++answer
            }
        }
        return answer
    }
}

fun main() {
    repeat(readln().toInt()) {
        val st = StringTokenizer(readln())
        val id = st.nextToken().toInt()
        val numbers = IntArray(20) { st.nextToken().toInt() }

        val count = Problem10431().solution(numbers)
        println("$id $count")
    }
}
