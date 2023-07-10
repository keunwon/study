package com.keunwon.algorithm.baekjoon

/**
 * Title: 장난감 경주
 * Level: 실버-5
 **/
class Problem19592 {
    fun solution(x: Int, y: Int, speeds: DoubleArray): Int {
        val winner = speeds.take(speeds.lastIndex).minOf { x / it }
        var left = 0
        var right = y

        if (winner > x / speeds.last()) return 0
      
        while (left <= right) {
            val mid = (left + right) / 2
            val distance = (x - mid) / speeds.last() + 1

            if (winner > distance) right = mid - 1
            else left = mid + 1
        }
        return if (y < left) -1 else left
    }
}

fun main() {
    val t = readLine()!!.toInt()
    repeat(t) {
        val (n, x, y) = readLine()!!.split(" ").map { it.toInt() }
        val speeds = readLine()!!.split(" ").map { it.toDouble() }.toDoubleArray()

        Problem19592().solution(x, y, speeds).also { println(it) }
    }
}
