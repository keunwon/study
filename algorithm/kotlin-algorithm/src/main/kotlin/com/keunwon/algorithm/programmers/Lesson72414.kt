package com.keunwon.algorithm.programmers

class Lesson72414 {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val playSeconds = play_time.toSeconds()
        val advSeconds = adv_time.toSeconds()
        val prefixSum = LongArray(playSeconds + 1).apply {
            logs.forEach { log ->
                val (s, e) = log.split("-").map { it.toSeconds() }
                ++this[s]
                --this[e]
            }
            repeat(2) {
                for (i in 1 until size) {
                    this[i] += this[i - 1]
                }
            }
        }

        var max = prefixSum[advSeconds - 1]
        var sIndex = 0

        for (i in advSeconds..playSeconds) {
            val tmpIndex = i - advSeconds
            val seconds = prefixSum[i] - prefixSum[tmpIndex]

            if (max < seconds) {
                max = seconds
                sIndex = tmpIndex + 1
            }
        }
        return String.format(
            "%02d:%02d:%02d",
            sIndex / 3600,
            sIndex / 60 % 60,
            sIndex % 60,
        )
    }

    private fun String.toSeconds(): Int {
        val (h, m, s) = split(":").map { it.toInt() }
        return h * 3600 + m * 60 + s
    }
}