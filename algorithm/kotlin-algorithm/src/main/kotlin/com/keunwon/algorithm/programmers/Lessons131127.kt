package com.keunwon.algorithm.programmers

/**
 * Title: 할인 행사
 * Level: 2
 **/
class Lessons131127 {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val map = want.mapIndexed { index, name -> name to number[index] }.toMap()
        var answer = 0

        for (i in 0 until discount.size - 9) {
            val list = discount.slice(i until i + 10)
            val tmp = list.groupingBy { it }.eachCount()
            val isValid = map.all { tmp.getOrDefault(it.key, 0) == it.value }

            if (isValid) answer++
        }
        return answer
    }

    fun solution2(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        val map = want.mapIndexed { index, name -> name to number[index] }.toMap()
        return (0..discount.size - 10).count { i ->
            val cart = discount
                .slice(i until i + 10)
                .groupingBy { it }
                .eachCount()
            cart.all { map[it.key] == it.value }
        }
    }
}
