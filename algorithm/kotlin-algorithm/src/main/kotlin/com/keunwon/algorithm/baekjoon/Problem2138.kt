package com.keunwon.algorithm.baekjoon

import kotlin.math.min

class Problem2138 {
    private var answer = Int.MAX_VALUE

    fun solution(start: String, end: String): Int {
        val arr1 = IntArray(start.length) { start[it].digitToInt() }
        val arr2 = IntArray(start.length) { start[it].digitToInt() }.apply {
            switching(this, 0)
        }
        val target = IntArray(end.length) { end[it].digitToInt() }

        dfs(1, 0, arr1, target)
        dfs(1, 1, arr2, target)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(index: Int, count: Int, arr: IntArray, target: IntArray) {
        if (index == arr.size) {
            if (arr.last() == target.last()) answer = min(answer, count)
            return
        }

        if (arr[index - 1] == target[index - 1]) {
            dfs(index + 1, count, arr, target)
        } else {
            switching(arr, index)
            dfs(index + 1, count + 1, arr, target)
        }
    }

    private fun switching(arr: IntArray, index: Int) {
        for (i in index - 1..index + 1) {
            if (i in arr.indices) arr[i] = 1 xor arr[i]
        }
    }
}

fun main() {
    val n = readln().toInt()
    val start = readln()
    val end = readln()

    println(Problem2138().solution(start, end))
}
