package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 스위치 켜고 끄기
 * 난이도: 실버-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1244">스위치 켜고 끄기 (실버-4)</a>
 **/
class Problem1244 {
    fun solution(switches: IntArray, students: Array<IntArray>): IntArray {
        for ((type, num) in students) {
            if (type == 1) {
                for (i in num - 1 until switches.size step num) {
                    switches[i] = 1 xor switches[i]
                }
            } else if (type == 2) {
                var left = num - 1
                var right = num - 1

                while (left - 1 in switches.indices && right + 1 in switches.indices) {
                    if (switches[left - 1] != switches[right + 1]) break
                    --left
                    ++right
                }

                for (i in left..right) {
                    switches[i] = 1 xor switches[i]
                }
            }
        }
        return switches
    }
}

fun main() {
    val n = readln().toInt()
    val switches = run {
        val arr = readln().split(" ")
        IntArray(n) { arr[it].toInt() }
    }
    val k = readln().toInt()
    val students = Array(k) {
        val arr = readln().split(" ")
        IntArray(2) { arr[it].toInt() }
    }

    Problem1244().solution(switches, students)
        .toList()
        .chunked(20)
        .forEach { println(it.joinToString(" ")) }
}
