package com.keunwon.algorithm.againresolve

class ALessons42587 {
    fun solution(priorities: IntArray, location: Int): Int {
        var order = 0
        val deque = ArrayDeque<Pair<Int, Int>>().apply {
            priorities.forEachIndexed { i, p -> this.add(i to p) }
        }

        while (deque.isNotEmpty()) {
            val max = deque.maxOf { it.second }
            val cur = deque.removeFirst()

            if (max == cur.second) {
                ++order
                if (location == cur.first) break
            } else deque.addLast(cur)
        }
        return order
    }
}

fun main() {
    ALessons42587().solution(intArrayOf(2, 1, 3, 2), 2).also(::println)
    ALessons42587().solution(intArrayOf(1, 1, 9, 1, 1, 1), 0).also(::println)
}
