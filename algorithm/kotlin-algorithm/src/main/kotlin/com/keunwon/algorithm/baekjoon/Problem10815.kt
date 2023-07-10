package com.keunwon.algorithm.baekjoon

/**
 * Title: 숫자 카드
 * Level: 실버-5
 **/
class Problem10815 {
    fun solution(arr1: IntArray, arr2: IntArray): IntArray {
        val set = arr1.toSet()
        return arr2.map { if (set.contains(it)) 1 else 0 }.toIntArray()
    }
}

fun main() {
    val createArray = {
        val size = readLine()!!.toInt()
        val readLine = readLine()!!.split(" ")
        IntArray(size) { readLine[it].toInt() }
    }
    val arr1 = createArray()
    val arr2 = createArray()

    Problem10815().solution(arr1, arr2).also { println(it.joinToString(" ")) }
}
