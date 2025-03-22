package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 0 만들기
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/7490">0 만들기 (골드-5)</a>
 **/
class Problem7490 {
    private val result = mutableListOf<String>()

    fun solution(n: Int): Array<String> {
        combination(1, "1", n)
        return result.sorted().toTypedArray()
    }

    private fun combination(cur: Int, str: String, n: Int) {
        if (cur == n) {
            val expression = str.replace(" ", "")
            val numbers = expression.split("[+-]".toRegex()).map { it.toInt() }
            val operators = expression.replace("\\d".toRegex(), "").toCharArray()
            var sum = numbers[0]

            for ((i, op) in operators.withIndex()) {
                when (op) {
                    '+' -> sum += numbers[i + 1]
                    '-' -> sum -= numbers[i + 1]
                }
            }

            if (sum == 0) result.add(str)
            return
        }

        for (c in charArrayOf('+', '-', ' ')) {
            combination(cur + 1, "$str$c${cur + 1}", n)
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()
    repeat(t) {
        val n = br.readLine().toInt()
        Problem7490().solution(n).forEach {
            bw.write(it)
            bw.newLine()
        }
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}
