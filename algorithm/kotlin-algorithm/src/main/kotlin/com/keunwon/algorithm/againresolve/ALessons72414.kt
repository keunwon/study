package com.keunwon.algorithm.againresolve

class ALessons72414 {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val playTime = play_time.toSecond()
        val prefixSum = LongArray(playTime + 1).also { arr ->
            logs.forEach { log ->
                val (start, end) = log.split("-").map { it.toSecond() }
                arr[start]++
                arr[end]--
            }
            repeat(2) {
                for (i in 1 until arr.size) arr[i] += arr[i - 1]
            }
        }
        val advTime = adv_time.toSecond()
        var max = prefixSum[advTime - 1]
        var startTime = 0

        for (i in advTime..playTime) {
            val index = i - advTime
            val diff = prefixSum[i] - prefixSum[index]

            if (max < diff) {
                max = diff
                startTime = index + 1
            }
        }

        return "%02d:%02d:%02d".format(
            startTime / 3600,
            startTime / 60 % 60,
            startTime % 60,
        )
    }

    private fun String.toSecond(): Int {
        val (h, m, s) = split(":").map { it.toInt() }
        return h * 3600 + m * 60 + s
    }
}

fun main() {
    val playTime = "02:03:55"
    val advTime = "00:14:15"
    val logs = arrayOf(
        "01:20:15-01:45:14",
        "00:40:31-01:00:00",
        "00:25:50-00:48:29",
        "01:30:59-01:53:29",
        "01:37:44-02:02:30",
    )

    ALessons72414().solution(playTime, advTime, logs).also(::println)
}
