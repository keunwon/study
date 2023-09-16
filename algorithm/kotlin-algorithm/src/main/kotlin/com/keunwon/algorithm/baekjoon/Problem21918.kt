package com.keunwon.algorithm.baekjoon

/**
 * Title: 전구
 * Level: 브론즈-2
 **/
class Problem21918 {
    fun solution(lights: IntArray, commands: Array<IntArray>): IntArray {
        commands.forEach { (a, b, c) ->
            when (a) {
                1 -> lights[b - 1] = c

                2 -> {
                    (b - 1 until c).forEach { lights[it] = lights[it] xor 1 }
                }

                3 -> {
                    (b - 1 until c).forEach { lights[it] = 0 }
                }

                4 -> {
                    (b - 1 until c).forEach { lights[it] = 1 }
                }
            }
        }
        return lights
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val readIntArray = { br.readLine().split(" ").map(String::toInt).toIntArray() }
        val (n, m) = readIntArray()
        val lights = readIntArray()
        val commands = Array(m) { readIntArray() }

        Problem21918().solution(lights, commands).also {
            bw.write("${it.joinToString(" ")}")
        }
        bw.flush()
    }
}
