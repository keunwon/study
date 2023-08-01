package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons67258 {
    fun solution(gems: Array<String>): IntArray {
        val gemSet = gems.toSet()
        val map = mutableMapOf<String, Int>()
        val queue = LinkedList<String>()
        var startIndex = 0
        var tmpIndex = 0
        var length = gems.size

        for ((i, gem) in gems.withIndex()) {
            queue.offer(gem)
            map[gem] = map.getOrDefault(gem, 0) + 1

            while (true) {
                val cur = queue.peek()
                if (map.getValue(cur) > 1) {
                    queue.poll()
                    map[cur] = map.getValue(cur) - 1
                    tmpIndex++
                } else break
            }

            if (gemSet.size == map.size && queue.size < length) {
                startIndex = tmpIndex
                length = queue.size
            }
        }
        return intArrayOf(startIndex + 1, startIndex + length)
    }
}

fun main() {
    arrayOf(
        arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"),
        arrayOf("AA", "AB", "AC", "AA", "AC"),
        arrayOf("XYZ", "XYZ", "XYZ"),
        arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB"),
    ).forEach { gems -> ALessons67258().solution(gems).also { println(it.joinToString(", ")) } }
}
