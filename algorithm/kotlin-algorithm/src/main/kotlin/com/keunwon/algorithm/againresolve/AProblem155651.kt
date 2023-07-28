package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem155651 {
    fun solution(book_time: Array<Array<String>>): Int {
        val times = book_time.map { (start, end) -> Time(start.toMinute(), end.toMinute() + 10) }
            .sortedWith(compareBy({ it.start }, { it.end }))
        val queue = PriorityQueue<Time>(compareBy { it.end })

        for (time in times) {
            if (queue.isEmpty()) {
                queue.offer(time)
                continue
            }

            if (time.start >= queue.peek().end) queue.poll()
            queue.offer(time)
        }
        return queue.size
    }

    private fun String.toMinute(): Int {
        val (h, m) = split(":").map { it.toInt() }
        return h * 60 + m
    }

    private data class Time(val start: Int, val end: Int)
}

fun main() {
    val bookTime = arrayOf(
        arrayOf("15:00", "17:00"),
        arrayOf("16:40", "18:20"),
        arrayOf("14:20", "15:20"),
        arrayOf("14:10", "19:20"),
        arrayOf("18:20", "21:20"),
    )
    AProblem155651().solution(bookTime).also { println(it) }
}
