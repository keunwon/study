package com.keunwon.algorithm.baekjoon

/**
 * Title: 과제 안 내신 분..?
 * Level: 브론즈-5
 **/
class Problem5597 {
    fun solution(arr: IntArray): Pair<Int, Int> {
        val visited = BooleanArray(31).apply {
            arr.forEach { this[it] = true }
        }
        val list = (1..30).filter { !visited[it] }
        return list[0] to list[1]
    }
}

fun main() {
    val arr = IntArray(28) { readLine()!!.toInt() }
    Problem5597().solution(arr).also {
        println(it.first)
        println(it.second)
    }
}
