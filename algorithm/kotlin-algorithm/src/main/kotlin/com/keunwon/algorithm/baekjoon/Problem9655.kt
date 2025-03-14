package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 돌 게임
 * 난이도: 실버-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/9655">돌 게임 (실버-5)</a>
 **/
class Problem9655 {
    fun solution(n: Int): String {
        var ret = n % 6
        var flag = false

        while (ret > 0) {
            if (ret > 2) ret -= 3 else --ret
            flag = !flag
        }
        return if (flag) "SK" else "CY"
    }
}

fun main() {
    val n = readln().toInt()
    Problem9655().solution(n).also { println(it) }
}
