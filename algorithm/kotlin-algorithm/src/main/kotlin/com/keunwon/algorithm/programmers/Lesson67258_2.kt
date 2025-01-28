package com.keunwon.algorithm.programmers

import java.util.*

class Lesson67258_2 {
    fun solution(gems: Array<String>): IntArray {
        val result = intArrayOf(0, gems.lastIndex)
        val gemTypes = gems.toHashSet()
        val q = LinkedList<String>()
        val map = mutableMapOf<String, Int>()
        var sIdx = 0

        for ((i, gem) in gems.withIndex()) {
            q.offer(gem)
            map[gem] = map.getOrDefault(gem, 0) + 1

            while (q.isNotEmpty() && map.getValue(q.peek()) > 1) {
                val key = q.poll()
                map[key] = map.getValue(key) - 1
                ++sIdx
            }

            if (map.size == gemTypes.size && result[1] - result[0] > i - sIdx) {
                result[0] = sIdx
                result[1] = i
            }
        }

        ++result[0]
        ++result[1]
        return result
    }
}
