package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 호텔 대실
 * Level: 2
 **/
class Lessons155651 {
    fun solution(book_time: Array<Array<String>>): Int {
        val times = book_time.map { arr ->
            val start = toSecond(arr[0])
            val end = toSecond(arr[1]) + toSecond("00:10")
            start to end
        }.sortedWith(compareBy({ it.first }, { it.second }))
        val queue = PriorityQueue<Int>()

        for ((start, end) in times) {
            if (queue.isEmpty()) {
                queue.offer(end)
                continue
            }
            if (queue.peek() <= start) queue.poll()
            queue.offer(end)
        }
        return queue.size
    }

    private fun toSecond(time: String): Int {
        val (hour, minute) = time.split(":").map(String::toInt)
        return hour * 60 + minute
    }
}
