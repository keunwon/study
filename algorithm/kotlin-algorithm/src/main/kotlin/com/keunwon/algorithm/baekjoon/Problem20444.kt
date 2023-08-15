package com.keunwon.algorithm.baekjoon

/**
 * Title: 색종이와 가위
 * Level: 골드-5
 **/
class Problem20444 {
    fun solution(n: Long, k: Long): String {
        var left = 0L
        var right = n / 2

        while (left <= right) {
            val row = (left + right) / 2
            val col = n - row
            val page = (row + 1) * (col + 1)

            when {
                page == k -> return "YES"
                page < k -> left = row + 1
                page > k -> right = row - 1
            }
        }
        return "NO"
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toLong() }
    Problem20444().solution(n, k).also(::println)
}
