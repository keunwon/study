package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons176962 {
    fun solution(plans: Array<Array<String>>): Array<String> {
        val stack = Stack<Study>()
        val answer = mutableListOf<String>()
        val studyList = plans.map { (subject, startTime, playTime) ->
            Study(subject, startTime.toSecond(), playTime.toInt())
        }.sortedBy { it.startTime }

        for (i in 0 until studyList.lastIndex) {
            val cur = studyList[i]
            val next = studyList[i + 1]

            if (cur.endTime <= next.startTime) {
                answer.add(cur.subject)

                var ret = next.startTime - cur.endTime
                while (ret > 0 && stack.isNotEmpty()) {
                    val diff = stack.peek().playTime - ret
                    ret -= stack.peek().playTime
                    stack.peek().playTime = diff.coerceAtLeast(0)

                    if (diff <= 0) answer.add(stack.pop().subject)
                }
            } else {
                cur.playTime = cur.endTime - next.startTime
                stack.push(cur)
            }
        }

        answer.add(studyList.last().subject)
        while (stack.isNotEmpty()) {
            answer.add(stack.pop().subject)
        }
        return answer.toTypedArray()
    }

    private fun String.toSecond(): Int =
        split(":").let { (h, m) -> h.toInt() * 60 + m.toInt() }

    private class Study(val subject: String, val startTime: Int, var playTime: Int) {
        val endTime: Int
            get() = startTime + playTime
    }
}

fun main() {
    ALessons176962().solution(
        arrayOf(
            arrayOf("korean", "11:40", "30"),
            arrayOf("english", "12:10", "20"),
            arrayOf("math", "12:30", "40")
        )
    ).also { println(it.contentToString()) }

    ALessons176962().solution(
        arrayOf(
            arrayOf("science", "12:40", "50"),
            arrayOf("music", "12:20", "40"),
            arrayOf("history", "14:00", "30"),
            arrayOf("computer", "12:30", "100")
        )
    ).also { println(it.contentToString()) }

    ALessons176962().solution(
        arrayOf(
            arrayOf("aaa", "12:00", "20"),
            arrayOf("bbb", "12:10", "30"),
            arrayOf("ccc", "12:40", "10")
        )
    ).also { println(it.contentToString()) }
}
