package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 보석 쇼핑
 * Level: 3
 **/
class Lessons67258 {
    fun solution(gems: Array<String>): IntArray {
        val gemSet = gems.toSet()
        val gemCountMap = mutableMapOf<String, Int>()
        val queue = LinkedList<String>()

        var start = 0
        var tmpStart = 0
        var size = gems.size

        for (gem in gems) {
            gemCountMap[gem] = gemCountMap.getOrDefault(gem, 0) + 1
            queue.offer(gem)

            while (true) {
                val g = queue.peek()
                if (gemCountMap.getValue(g) > 1) {
                    gemCountMap[g] = gemCountMap.getValue(g) - 1
                    queue.poll()
                    tmpStart++
                } else break
            }

            if (gemSet.size == gemCountMap.size && queue.size < size) {
                start = tmpStart
                size = queue.size
            }
        }
        return intArrayOf(start + 1, start + size)
    }
}
