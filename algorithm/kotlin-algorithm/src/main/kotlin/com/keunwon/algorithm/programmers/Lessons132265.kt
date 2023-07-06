package com.keunwon.algorithm.programmers

/**
 * Title: 롤케이크 자르기
 * Level: 2
 **/
class Lessons132265 {
    fun solution(topping: IntArray): Int {
        val countMap = topping.toList()
            .groupingBy { it }
            .eachCount()
            .toMutableMap()
        val set = mutableSetOf<Int>()

        return topping.count { t ->
            set.add(t)
            countMap[t] = countMap.getValue(t) - 1
            if (countMap[t] == 0) countMap.remove(t)
            set.size == countMap.size
        }
    }
}
