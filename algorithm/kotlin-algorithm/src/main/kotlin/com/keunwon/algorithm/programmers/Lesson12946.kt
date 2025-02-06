package com.keunwon.algorithm.programmers

class Lesson12946 {
    private var result = mutableListOf<IntArray>()

    fun solution(n: Int): Array<IntArray> {
        hanoi(n, 1, 2, 3)
        return result.toTypedArray()
    }

    fun hanoi(n: Int, start: Int, mid: Int, end: Int) {
        if (n == 0) return

        hanoi(n - 1, start, end, mid)
        result.add(intArrayOf(start, end))
        hanoi(n - 1, mid, start, end)
    }
}
