package algorithm.programmers

import java.util.Stack

class Lesson176962 {
    fun solution(plans: Array<Array<String>>): Array<String> {
        val times = plans.map { (subject, s, p) -> Time(subject, s.toMinutes(), p.toInt()) }
            .sortedBy { it.start }
        val stack = Stack<Time>()
        val answer = mutableListOf<String>()

        for (i in 0 until times.lastIndex) {
            val cur = times[i]
            val next = times[i + 1]

            if (cur.end <= next.start) {
                answer.add(cur.subject)

                var ret = next.start - cur.end
                while (0 < ret && stack.isNotEmpty()) {
                    val diff = stack.peek().play - ret
                    ret -= stack.peek().play
                    stack.peek().play = diff

                    if (diff <= 0) answer.add(stack.pop().subject)
                }
            } else {
                cur.play = cur.end - next.start
                stack.push(cur)
            }
        }
        answer.add(times.last().subject)
        while (stack.isNotEmpty()) {
            answer.add(stack.pop().subject)
        }
        return answer.toTypedArray()
    }

    private data class Time(
        val subject: String,
        val start: Int,
        var play: Int,
    ) {
        val end: Int
            get() = start + play
    }

    private fun String.toMinutes(): Int {
        val (h, m) = split(":").map { it.toInt() }
        return h * 60 + m
    }
}
