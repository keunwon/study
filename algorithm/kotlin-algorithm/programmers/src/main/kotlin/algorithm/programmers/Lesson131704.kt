package algorithm.programmers

import java.util.*

class Lesson131704 {
    fun solution(order: IntArray): Int {
        val stack = Stack<Int>()
        var orderIndex = 0
        var answer = 0

        for (i in 1..order.size) {
            stack.push(i)

            while (stack.isNotEmpty() && stack.peek() == order[orderIndex]) {
                stack.pop()
                ++orderIndex
                ++answer
            }
        }
        return answer
    }
}
