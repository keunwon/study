package com.keunwon.algorithm.baekjoon

/**
 * Title: 단절점과 단절선
 * Level: 실버-1
 **/
class Problem14675 {
    fun solution(
        arr1: Array<Pair<Int, Int>>,
        arr2: Array<Pair<Int, Int>>,
    ): Array<String> {
        val countMap = mutableMapOf<Int, Int>()

        arr1.forEach { (a, b) ->
            countMap[a] = countMap.getOrDefault(a, 0).inc()
            countMap[b] = countMap.getOrDefault(b, 0).inc()
        }

        return arr2.map { (a, b) ->
            if (a == 1 && countMap.getOrDefault(b, 0) < 2) "no"
            else "yes"
        }.toTypedArray()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val readArray = {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b) -> a to b }
    }
    val arr1 = Array(n - 1) { readArray() }
    val q = readLine()!!.toInt()
    val arr2 = Array(q) { readArray() }

    Problem14675().solution(arr1, arr2).forEach(::println)
}
