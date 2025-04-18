package algorithm.programmers

import java.util.*

class Lesson42628 {
    fun solution(operations: Array<String>): IntArray {
        val q1 = PriorityQueue<Int>()
        val q2 = PriorityQueue<Int>(compareByDescending { it })

        for (operation in operations) {
            val (option, num) = operation.split(" ").let { it[0] to it[1].toInt() }

            if (option == "I") {
                q1.offer(num)
                q2.offer(num)
            } else if (option == "D") {
                if (num == 1) q1.remove(q2.poll())
                else if (num == -1) q2.remove(q1.poll())
            }
        }

        if (q1.isEmpty()) return intArrayOf(0, 0)
        return intArrayOf(q2.peek(), q1.peek())
    }
}
