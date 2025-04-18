package algorithm.programmers

import java.util.*

class Lesson159994 {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        val queue1 = LinkedList(cards1.toList())
        val queue2 = LinkedList(cards2.toList())

        for (c in goal) {
            if (queue1.isNotEmpty() && queue1.peek() == c) {
                queue1.poll()
            } else if (queue2.isNotEmpty() && queue2.peek() == c) {
                queue2.poll()
            } else {
                return "No"
            }
        }
        return "Yes"
    }
}
