package com.keunwon.algorithm.programmers

/**
 * Title: 두 개 뽑아서 더하기
 * Level: 1
 **/
class Lessons68644 {
    fun solution(numbers: IntArray): IntArray {
        val answer = mutableSetOf<Int>()
        for (i in numbers.indices) {
            for (j in numbers.indices) {
                if (i != j) answer.add(numbers[i] + numbers[j])
            }
        }
        return answer.toIntArray().sortedArray()
    }
}
