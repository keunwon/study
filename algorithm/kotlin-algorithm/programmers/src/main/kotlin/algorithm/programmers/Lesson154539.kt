package algorithm.programmers

import java.util.Stack

class Lesson154539 {
    fun solution(numbers: IntArray): IntArray {
        val result = IntArray(numbers.size) { -1 }
        val stack = Stack<Int>()

        numbers.forEachIndexed { index, n ->
            while (stack.isNotEmpty() && n > numbers[stack.peek()]) {
                val idx = stack.pop()
                result[idx] = n
            }
            stack.push(index)
        }
        return result

    }
}
