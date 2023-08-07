package com.keunwon.algorithm.baekjoon

/**
 * Title: 듣보잡
 * Level: 실버-4
 **/
class Problem1764 {
    fun solution(arr1: Array<String>, arr2: Array<String>): Pair<Int, List<String>> {
        return (arr1 + arr2).groupingBy { it }
            .eachCount()
            .filter { it.value != 1 }
            .let { it.size to it.keys.sorted() }
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }

    Problem1764().solution(
        Array(n) { readLine()!! },
        Array(m) { readLine()!! },
    ).also { (count, list) ->
        println(count)
        list.forEach(::println)
    }
}
