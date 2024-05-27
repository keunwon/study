package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem2075 {
    fun solution(map: Array<IntArray>): Int {
        val queue = PriorityQueue<Int>()

        for (i in map.indices) {
            for (num in map[i]) {
                if (queue.size == map.size) {
                    if (queue.peek() < num) {
                        queue.poll()
                        queue.offer(num)
                    }
                } else {
                    queue.offer(num)
                }
            }
        }
        return queue.peek()
    }
}

fun main() {
    val n = readln().toInt()
    val map = Array(n) {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    println(Problem2075().solution(map))
}
