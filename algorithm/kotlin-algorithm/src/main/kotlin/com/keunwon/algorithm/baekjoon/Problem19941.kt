package com.keunwon.algorithm.baekjoon

/**
 * Title: 햄버거 분배
 * Level: 실버-3
 **/
class Problem19941 {
    fun solution(k: Int, food: CharArray): Int {
        val visited = BooleanArray(food.size)

        for ((i, w) in food.withIndex()) {
            if (w != 'P') continue

            val begin = (i - k).coerceAtLeast(0)
            val end = (i + k).coerceAtMost(food.lastIndex)

            for (j in begin..end) {
                if (food[j] == 'H' && !visited[j]) {
                    visited[j] = true
                    break
                }
            }
        }
        return visited.count { it }
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val food = readLine()!!.toCharArray()

    Problem19941().solution(k, food).also { println(it) }
}
