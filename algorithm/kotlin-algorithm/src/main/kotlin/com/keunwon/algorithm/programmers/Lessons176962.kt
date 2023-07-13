package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 과제 진행하기
 * Level: 2
 **/
class Lessons176962 {
    fun solution(plans: Array<Array<String>>): Array<String> {
        val stack = Stack<Lesson>()
        val answer = mutableListOf<String>()
        val lessons = plans.map { (subject, start, playTime) ->
            Lesson(subject, toMinute(start), playTime.toInt())
        }.sortedBy { it.startTime }

        for (i in 0 until lessons.lastIndex) {
            val cur = lessons[i]
            val next = lessons[i + 1]

            if (cur.endTime <= next.startTime) {
                answer.add(cur.subject)

                var ret = next.startTime - cur.endTime
                while (0 < ret && stack.isNotEmpty()) {
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
        answer.add(lessons.last().subject)
        while (stack.isNotEmpty()) answer.add(stack.pop().subject)
        return answer.toTypedArray()
    }

    private fun toMinute(time: String): Int {
        val (hour, minute) = time.split(":").map { it.toInt() }
        return hour * 60 + minute
    }

    private data class Lesson(
        val subject: String,
        val startTime: Int,
        var playTime: Int,
    ) {
        val endTime: Int
            get() = startTime + playTime
    }
}
