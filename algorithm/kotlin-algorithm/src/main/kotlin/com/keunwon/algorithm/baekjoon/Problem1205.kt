package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem1205 {
    fun solution(target: Int, p: Int, numbers: IntArray): Int {
        numbers.sortDescending()

        if (numbers.size == p && numbers.last() >= target) return -1

        var rank = 1
        for (n in numbers) {
            if (n > target) ++rank
            else break
        }
        return rank
    }
}

fun main() {
    val (n, target, p) = readln().split(" ").map { it.toInt() }

    if (n == 0) {
        println(1)
        return
    }

    val numbers = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    println(Problem1205().solution(target, p, numbers))
}
