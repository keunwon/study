package algorithm.programmers

import java.util.*

class Lesson154539 {
    fun solution(numbers: IntArray): IntArray {
        val answer = IntArray(numbers.size)
        val stack = Stack<Int>()

        for (i in numbers.lastIndex downTo 0) {
            while (stack.isNotEmpty()) {
                if (stack.peek() > numbers[i]) {
                    answer[i] = stack.peek()
                    break
                }
                stack.pop()
            }

            if (stack.isEmpty()) answer[i] = -1
            stack.push(numbers[i])
        }
        return answer
    }
}
