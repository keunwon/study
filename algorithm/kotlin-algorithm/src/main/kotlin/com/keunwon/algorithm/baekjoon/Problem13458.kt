package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: 시험 감독
 * 난이도: 브론즈-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/13458">시험 감독 (브론즈-2)</a>
 **/
class Problem13458 {
    fun solution(students: IntArray, b: Int, c: Int): Long {
        return students.fold(students.size.toLong()) { acc, n ->
            val ret = n - b

            if (ret > 0) {
                val div = ret / c
                val plus = if (ret % c == 0) div else div + 1
                acc + plus
            } else {
                acc
            }
        }
    }
}

fun main() {
    val a = readln().toInt()
    val st = StringTokenizer(readln())
    val students = IntArray(a) { st.nextToken().toInt() }
    val (b, c) = readln().split(" ").map { it.toInt() }

    println(Problem13458().solution(students, b, c))
}
