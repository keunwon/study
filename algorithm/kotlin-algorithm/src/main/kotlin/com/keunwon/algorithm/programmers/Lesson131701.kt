package com.keunwon.algorithm.programmers

class Lesson131701 {
    fun solution(elements: IntArray): Int {
        val answer = mutableSetOf<Int>()

        for (size in 1..elements.size) {
            for (i in elements.indices) {
                val sum = (i until i + size).sumOf { elements[it % elements.size] }
                if (!answer.contains(sum)) answer.add(sum)
            }
        }
        return answer.size
    }
}
