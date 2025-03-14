package com.keunwon.algorithm.programmers

/**
 * <p>
 * 이름: 집합
 * 난이도: 실버-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/11723">집합 (실버-5)</a>
 **/
class Lesson11723 {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        val n = br.readLine().toInt()
        var mask = 0

        repeat(n) {
            val (command, shift) = br.readLine().split(" ").let {
                val shift = if (it.size > 1) 1 shl it[1].toInt() else 0
                it[0] to shift
            }
            when (command) {
                "add" -> mask = mask or shift
                "all" -> mask = (1 shl 21) - 1

                "check" -> {
                    bw.write(if (mask and shift == shift) "1" else "0")
                    bw.newLine()
                }

                "empty" -> mask = 0
                "remove" -> mask = mask and shift.inv()
                "toggle" -> mask = mask xor shift
            }
        }

        bw.flush()
        bw.close()
        br.close()
    }
}

fun main() {
    Lesson11723().solution()
}
