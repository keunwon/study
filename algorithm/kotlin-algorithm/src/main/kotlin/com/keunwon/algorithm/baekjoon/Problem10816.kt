package com.keunwon.algorithm.baekjoon

/**
 * Title: 숫자 카드 2
 * Level: 실버-4
 **/
class Problem10816 {
    fun solution(arr1: IntArray, arr2: IntArray): IntArray {
        val map = arr1.toList().groupingBy { it }.eachCount()
        return arr2.map { map.getOrDefault(it, 0) }.toIntArray()
    }
}

fun main() {
    val createArray = {
        readLine()!!
        readLine()!!.split(" ").map(String::toInt).toIntArray()
    }
    val arr1 = createArray()
    val arr2 = createArray()

    Problem10816().solution(arr1, arr2).also { println(it.joinToString(" ")) }
}
