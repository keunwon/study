package com.keunwon.algorithm.baekjoon

/**
 * Title: 스위치 켜고 끄기
 * Level: 실버-4
 **/
class Problem1244 {
    fun solution(status: BooleanArray, commands: Array<Pair<Int, Int>>): IntArray {
        for ((type, num) in commands) {
            if (type == 1) {
                for (i in num - 1 until status.size step num) {
                    status[i] = !status[i]
                }
            } else if (type == 2) {
                var left = num - 1
                var right = num - 1

                while (left - 1 in status.indices && right + 1 in status.indices) {
                    if (status[left - 1] != status[right + 1]) break
                    left--
                    right++
                }
                for (i in left..right) status[i] = !status[i]
            }
        }
        return status.map { if (it) 1 else 0 }.toIntArray()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val status = readLine()!!.split(" ").map { it == "1" }.toBooleanArray()
    val m = readLine()!!.toInt()
    val commands = Array(m) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b) -> a to b }
    }

    Problem1244().solution(status, commands)
        .toList()
        .chunked(20)
        .forEach { println(it.joinToString(" ")) }
}
