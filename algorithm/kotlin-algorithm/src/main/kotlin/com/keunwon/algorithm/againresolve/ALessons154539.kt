package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons154539 {
    fun solution(numbers: IntArray): IntArray {
        val stack = Stack<Int>()
        val answer = IntArray(numbers.size)

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
