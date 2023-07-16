package com.keunwon.algorithm.baekjoon

/**
 * Title: 빗물
 * Level: 골드-5
 **/
class Problem14719 {
    fun solution(h: Int, w: Int, arr: IntArray): Int {
        val map = Array(h) { BooleanArray(w) }
        for ((i, num) in arr.withIndex()) {
            repeat(num) { map[h - it - 1][i] = true }
        }

        var answer = 0
        for (i in map.lastIndex downTo 0) {
            if (map[i].all { it }) continue

            var checked = false
            var tmpCount = 0

            for (j in map[0].indices) {
                if (map[i][j]) {
                    if (checked) {
                        answer += tmpCount
                        tmpCount = 0
                    } else checked = true
                    continue
                }

                if (checked && !map[i][j]) tmpCount++
            }
        }
        return answer
    }
}

fun main() {
    val (h, w) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    Problem14719().solution(h, w, arr).also { println(it) }
}
