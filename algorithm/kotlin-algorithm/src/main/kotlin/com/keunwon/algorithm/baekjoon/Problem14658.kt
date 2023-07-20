package com.keunwon.algorithm.baekjoon

/**
 * Title: 하늘에서 별똥별이 빗발친다
 * Level: 골드-3
 **/
class Problem14658 {
    fun solution(n: Int, m: Int, l: Int, stars: Array<Pair<Int, Int>>): Int {
        var max = 0

        for ((x, _) in stars) {
            for ((_, y) in stars) {
                val count = stars.count { (xx, yy) ->
                    x <= xx && xx <= x + l && y <= yy && yy <= y + l
                }
                max = max.coerceAtLeast(count)
            }
        }
        return stars.size - max
    }
}

fun main() {
    val (n, m, l, k) = readLine()!!.split(" ").map { it.toInt() }
    val stars = Array(k) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b) -> a to b }
    }

    Problem14658().solution(n, m, l, stars).also { println(it) }
}
