package com.keunwon.algorithm.baekjoon

/**
 * Title: 크로스 컨트리
 * Level: 실버-4
 **/
class Problem9017 {
    fun solution(arr: IntArray): Int {
        val countMap = arr.toList()
            .groupingBy { it }
            .eachCount()
            .filter { it.value == 6 }
            .also { if (it.size == 1) return it.keys.first() }
        val map = mutableMapOf<Int, MutableList<Int>>()
        var rank = 1

        for (num in arr) {
            if (num !in countMap.keys) continue

            val list = map[num] ?: mutableListOf()
            map[num] = list.apply { add(rank++) }
        }
        return map.toList().sortedWith(
            compareBy(
                { -it.second.take(4).sum() },
                { it.second.getOrElse(4) { Int.MAX_VALUE } },
            )
        ).first().first
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val n = readLine()!!.toInt()
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

        Problem9017().solution(arr).also { println(it) }
    }
}
