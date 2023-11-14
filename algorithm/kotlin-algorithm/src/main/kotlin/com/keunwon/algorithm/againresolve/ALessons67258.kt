package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons67258 {
    fun solution(gems: Array<String>): IntArray {
        val uniqueGems = gems.toSet()
        val gemQuantity = mutableMapOf<String, Int>()
        val queue = LinkedList<String>()
        var startIndex = 0
        var tmpStartIndex = 0
        var size = gems.size

        for (gem in gems) {
            gemQuantity[gem] = gemQuantity.getOrDefault(gem, 0) + 1
            queue.offer(gem)

            while (true) {
                val p = queue.peek()
                if (gemQuantity.getValue(p) < 2) break

                gemQuantity.computeIfPresent(p) { _, value -> value - 1 }
                ++tmpStartIndex
                queue.poll()
            }

            if (uniqueGems.size == gemQuantity.size && queue.size < size) {
                startIndex = tmpStartIndex
                size = queue.size
            }
        }
        return intArrayOf(startIndex + 1, startIndex + size)
    }
}

fun main() {
    ALessons67258().solution(
        arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")
    ).also { println(it.contentToString()) }

    ALessons67258().solution(
        arrayOf("AA", "AB", "AC", "AA", "AC")
    ).also { println(it.contentToString()) }

    ALessons67258().solution(
        arrayOf("XYZ", "XYZ", "XYZ")
    ).also { println(it.contentToString()) }

    ALessons67258().solution(
        arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB")
    ).also { println(it.contentToString()) }
}
