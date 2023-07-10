package com.keunwon.algorithm.baekjoon

/**
 * Title: 수 찾기
 * Level: 실버-4
 **/
class Problem1920 {
    fun solution(arr1: IntArray, arr2: IntArray): IntArray {
        val set = arr1.toSet()
        return arr2.map { if (set.contains(it)) 1 else 0 }.toIntArray()
    }
}

fun main() {
    val createArray = {
        readLine()!!.toInt()
        readLine()!!.split(" ").map(String::toInt).toIntArray()
    }
    val arr1 = createArray()
    val arr2 = createArray()

    Problem1920().solution(arr1, arr2).also { println(it.joinToString("\n")) }
}
