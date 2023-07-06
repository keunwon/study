package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 뒤에 있는 큰 수 찾기
 * Level: 2
 **/
class Lessons154539 {
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
            stack.add(numbers[i])
        }
        return answer
    }
}
