package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons155651 {
    fun solution(book_time: Array<Array<String>>): Int {
        val sortedTimes = book_time.map { (start, end) ->
            Pair(start.toSecond(), end.toSecond() + 10)
        }.sortedWith(compareBy({ it.first }, { it.second }))
        val waitQueue = PriorityQueue<Int>()

        sortedTimes.forEach { (start, end) ->
            if (waitQueue.isNotEmpty() && waitQueue.peek() <= start) waitQueue.poll()
            waitQueue.offer(end)
        }
        return waitQueue.size
    }

    private fun String.toSecond(): Int = split(":")
        .let { (hour, minute) -> hour.toInt() * 60 + minute.toInt() }
}

fun main() {
    ALessons155651().solution(
        arrayOf(
            arrayOf("15:00", "17:00"),
            arrayOf("16:40", "18:20"),
            arrayOf("14:20", "15:20"),
            arrayOf("14:10", "19:20"),
            arrayOf("18:20", "21:20")
        )
    ).also(::println)
}
