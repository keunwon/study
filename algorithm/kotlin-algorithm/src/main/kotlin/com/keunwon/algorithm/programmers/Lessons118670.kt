package com.keunwon.algorithm.programmers

/**
 * Title: 행렬과 연산
 * Level: 4
 **/
class Lessons118670 {
    fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
        val left = ArrayDeque<Int>()
        val mid = ArrayDeque<ArrayDeque<Int>>()
        val right = ArrayDeque<Int>()

        for (arr in rc) {
            val tmp = ArrayDeque<Int>()

            for ((j, num) in arr.withIndex()) {
                when (j) {
                    0 -> left.add(num)
                    arr.lastIndex -> right.add(num)
                    else -> tmp.add(num)
                }
            }
            mid.add(tmp)
        }

        operations.forEach { operator ->
            if (operator == "Rotate") {
                mid.first().addFirst(left.removeFirst())
                right.addFirst(mid.first().removeLast())
                mid.last().addLast(right.removeLast())
                left.addLast(mid.last().removeFirst())
            } else {
                left.addFirst(left.removeLast())
                mid.addFirst(mid.removeLast())
                right.addFirst(right.removeLast())
            }
        }

        val answer = Array(rc.size) { IntArray(rc[0].size) }
        for (i in rc.indices) {
            var j = 0

            answer[i][j++] = left.removeFirst()
            mid.removeFirst().forEach { num ->
                answer[i][j++] = num
            }
            answer[i][j] = right.removeFirst()
        }
        return answer
    }
}
