package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem14719 {
    fun solution(h: Int, w: Int, arr: IntArray): Int {
        val map = Array(h) { BooleanArray(w) }.apply {
            arr.mapIndexed { index, n -> repeat(n) { this[h - it - 1][index] = true } }
        }
        var answer = 0


        for (i in map.indices) {
            if (map[i].all { it }) continue

            val pre = map[i].indexOf(true)
            if (pre == -1) continue

            var count = 0
            for (j in pre + 1 until map[0].size) {
                if (map[i][j]) {
                    answer += count
                    count = 0
                } else {
                    ++count
                }
            }
        }
        return answer
    }
}

fun main() {
    val (h, w) = readln().split(" ").map { it.toInt() }
    val arr = run {
        val st = StringTokenizer(readln())
        IntArray(w) { st.nextToken().toInt() }
    }

    println(Problem14719().solution(h, w, arr))
}
