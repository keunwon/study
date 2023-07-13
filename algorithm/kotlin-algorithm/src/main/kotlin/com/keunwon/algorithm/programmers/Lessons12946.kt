package com.keunwon.algorithm.programmers

/**
 * Title: 하노이
 * Level: 2
 **/
class Lessons12946 {
    private val answer = mutableListOf<IntArray>()

    fun solution(n: Int): Array<IntArray> {
        hanoi(n, 1, 3, 2)
        return answer.toTypedArray()
    }

    private fun hanoi(n: Int, src: Int, dest: Int, transfer: Int) {
        if (n == 1) {
            answer.add(intArrayOf(src, dest))
            return
        }

        hanoi(n - 1, src, transfer, dest)
        answer.add(intArrayOf(src, dest))
        hanoi(n - 1, transfer, dest, src)
    }
}
