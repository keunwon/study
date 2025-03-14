package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 디지털 티비
 * 난이도: 브론즈-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2816">디지털 티비 (브론즈-1)</a>
 **/
class Problem2816 {
    fun solution(channels: Array<String>): String {
        val result = StringBuilder()

        for ((i, channel) in channels.withIndex()) {
            if (channel == "KBS1") {
                for (j in i downTo 1) {
                    val tmp = channels[j - 1]
                    channels[j - 1] = channels[j]
                    channels[j] = tmp
                    result.append("4")
                }
                break
            } else {
                result.append("1")
            }
        }

        if (channels[1] == "KBS2") return result.toString()

        for ((i, channel) in channels.withIndex()) {
            if (channel == "KBS2") {
                for (j in i downTo 2) {
                    val tmp = channels[j - 1]
                    channels[j - 1] = channels[j]
                    channels[j] = tmp
                    result.append("4")
                }
                break
            } else {
                result.append("1")
            }
        }

        return result.toString()
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val channels = Array(n) { br.readLine() }

    Problem2816().solution(channels).also { println(it) }
}
