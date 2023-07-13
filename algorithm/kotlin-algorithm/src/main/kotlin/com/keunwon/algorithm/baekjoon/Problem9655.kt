package com.keunwon.algorithm.baekjoon

/**
 * Title: 돌 게임
 * Level: 실버-5
 **/
class Problem9655 {
    fun solution(n: Int): String {
        var num = n
        var flag = false

        while (num != 0) {
            if (3 <= num) num -= 3 else num--
            flag = !flag
        }
        return if (flag) "SK" else "CY"
    }
}

fun main() {
    val n = readLine()!!.toInt()
    Problem9655().solution(n).also { println(it) }
}
