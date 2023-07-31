package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons176962 {
    fun solution(plans: Array<Array<String>>): Array<String> {
        val times = plans.map { (name, start, playTime) ->
            Study(name, start.toSecond(), playTime.toInt())
        }.sortedWith(compareBy({ it.startTime }, { it.playTime }))
        val stack = Stack<Study>()
        val answer = mutableListOf<String>()

        for (i in 0 until times.lastIndex) {
            val cur = times[i]
            val next = times[i + 1]

            if (cur.endTime > next.startTime) {
                val study = cur.copy(playTime = cur.endTime - next.startTime)
                stack.push(study)
                continue
            }
            answer.add(cur.name)

            var ret = next.startTime - cur.endTime
            while (stack.isNotEmpty() && ret > 0) {
                val study = stack.pop()
                val diff = study.playTime - ret

                if (diff > 0) {
                    stack.push(study.copy(playTime = diff))
                    break
                }
                answer.add(study.name)
                ret -= study.playTime
            }
        }
        answer.add(times.last().name)
        while (stack.isNotEmpty()) answer.add(stack.pop().name)
        return answer.toTypedArray()
    }

    private fun String.toSecond(): Int {
        val (h, m) = split(":").map { it.toInt() }
        return h * 60 + m
    }

    private data class Study(val name: String, val startTime: Int, val playTime: Int) {
        val endTime: Int = startTime + playTime
    }
}

fun main() {
    arrayOf(
        arrayOf(
            arrayOf("korean", "11:40", "30"),
            arrayOf("english", "12:10", "20"),
            arrayOf("math", "12:30", "40"),
        ),
        arrayOf(
            arrayOf("science", "12:40", "50"),
            arrayOf("music", "12:20", "40"),
            arrayOf("history", "14:00", "30"),
            arrayOf("computer", "12:30", "100"),
        ),
        arrayOf(
            arrayOf("aaa", "12:00", "20"),
            arrayOf("bbb", "12:10", "30"),
            arrayOf("ccc", "12:40", "10"),
        )
    ).forEach { plans -> ALessons176962().solution(plans).also { println(it.contentToString()) } }
}
