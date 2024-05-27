package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem1244 {
    fun solution(lights: BooleanArray, commands: Array<Pair<Int, Int>>): List<Int> {
        for ((gender, num) in commands) {
            when (gender) {
                1 -> {
                    for (i in num - 1 until lights.size step num) {
                        lights[i] = !lights[i]
                    }
                }

                2 -> {
                    var left = num - 1
                    var right = num - 1

                    while (true) {
                        val tl = left - 1
                        val tr = right + 1

                        if (tl !in lights.indices || tr !in lights.indices) break
                        if (lights[tl] != lights[tr]) break

                        --left
                        ++right
                    }

                    for (i in left..right) {
                        lights[i] = !lights[i]
                    }
                }
            }
        }
        return lights.map { if (it) 1 else 0 }
    }
}

fun main() {
    val n = readln().toInt()
    val lights = run {
        val st = StringTokenizer(readln())
        BooleanArray(n) { st.nextToken().toInt() == 1 }
    }
    val k = readln().toInt()
    val commands = Array(k) {
        val st = StringTokenizer(readln())
        Pair(st.nextToken().toInt(), st.nextToken().toInt())
    }

    val answer = Problem1244().solution(lights, commands).chunked(20)

    answer.forEach { println(it.joinToString(" ")) }
}
