package com.keunwon.algorithm.programmers

/**
 * Title: 광고 삽입
 * Level: 3
 **/
class Lessons72414 {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val playSecond = play_time.toSecond()
        val prefixSum = LongArray(playSecond + 1).also { arr ->
            logs.forEach { log ->
                val (start, end) = log.split("-").map { it.toSecond() }
                arr[start]++
                arr[end]--
            }
            repeat(2) {
                for (i in 1 until arr.size) arr[i] += arr[i - 1]
            }
        }
        val advSecond = adv_time.toSecond()
        var max = prefixSum[advSecond - 1]
        var startIndex = 0

        for (i in advSecond..playSecond) {
            val index = i - advSecond
            val second = prefixSum[i] - prefixSum[index]

            if (max < second) {
                max = second
                startIndex = index + 1
            }
        }
        return "%02d:%02d:%02d".format(
            startIndex / 3600,
            startIndex / 60 % 60,
            startIndex % 60,
        )
    }

    private fun String.toSecond(): Int {
        val (h, m, s) = split(":").map { it.toInt() }
        return h * 3600 + m * 60 + s
    }
}
