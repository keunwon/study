package com.keunwon.algorithm.baekjoon

import kotlin.math.max
import kotlin.math.min

class Problem19941 {
    fun solution(k: Int, arr: CharArray): Int {
        val visited = BooleanArray(arr.size)

        for (i in arr.indices) {
            if (arr[i] != 'P') continue

            val sIndex = max(0, i - k)
            val eIndex = min(visited.lastIndex, i + k)

            for (j in sIndex..eIndex) {
                if (arr[j] == 'H' && !visited[j]) {
                    visited[j] = true
                    break
                }
            }
        }
        return visited.count { it }
    }
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val arr = readln().toCharArray()

    println(Problem19941().solution(k, arr))
}
