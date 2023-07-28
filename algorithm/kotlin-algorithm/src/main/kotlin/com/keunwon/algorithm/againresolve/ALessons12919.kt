package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons12919 {
    fun solution(s: String, t: String): Int {
        val queue = LinkedList<String>().apply { offer(t) }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.isBlank()) break
            if (cur == s) return 1

            if (cur.last() == 'A') queue.offer(cur.take(cur.lastIndex))
            if (cur.first() == 'B') queue.offer(cur.substring(1).reversed())
        }
        return 0
    }
}

fun main() {
    val s = readLine()!!
    val t = readLine()!!

    ALessons12919().solution(s, t).also { println(it) }
}
