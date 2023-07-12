package com.keunwon.algorithm.programmers

/**
 * Title: 시소 짝꿍
 * Level: 2
 **/
class Lessons152996 {
    fun solution(weights: IntArray): Long {
        var answer = 0L
        val map = mutableMapOf<Double, Int>()

        weights.sorted().forEach { weight ->
            val arr = doubleArrayOf(
                weight * 1.0,
                weight * 1.0 / 2,
                weight * 2.0 / 3,
                weight * 3.0 / 4,
            )
            arr.forEach { if (map.contains(it)) answer += map.getValue(it) }
            map[weight * 1.0] = map.getOrDefault(weight * 1.0, 0) + 1
        }
        return answer
    }
}
