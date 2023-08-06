package com.keunwon.algorithm.baekjoon

/**
 * Title: 경고
 * Level: 브론즈-3
 **/
class Problem3029 {
    fun solution() {
        val cur = readLine()!!.split(":")
            .map { it.toInt() }
            .toIntArray()
        val dest = readLine()!!.split(":")
            .map { it.toInt() }
            .toIntArray()

        if (cur.contentEquals(dest)) {
            println("24:00:00")
            return
        }

        val arr = IntArray(3)
        for (i in arr.indices) {
            arr[i] = dest[i] - cur[i]
        }

        if (arr[2] < 0) {
            arr[2] += 60
            arr[1]--
        }
        if (arr[1] < 0) {
            arr[1] += 60
            arr[0]--
        }
        if (arr[0] < 0) {
            arr[0] += 24
        }

        val answer = "%02d:%02d:%02d".format(arr[0], arr[1], arr[2])
        println(answer)
    }

    private data class Time(val hour: Int, val minute: Int, val second: Int)
}

fun main() {
    Problem3029().solution()
}
