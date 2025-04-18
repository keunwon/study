package algorithm.programmers

import java.util.*

class Lesson67258 {
    fun solution(gems: Array<String>): IntArray {
        val gemType = gems.toSet()
        val quantities = mutableMapOf<String, Int>()
        val queue = LinkedList<String>()

        var sIndex = 0
        var tmpIndex = 0
        var size = gems.size

        for (gem in gems) {
            quantities[gem] = quantities.getOrDefault(gem, 0) + 1
            queue.offer(gem)

            while (queue.isNotEmpty() && quantities.getValue(queue.peek()) > 1) {
                val cur = queue.poll()
                quantities[cur] = quantities.getValue(cur) - 1
                tmpIndex++
            }

            if (gemType.size == quantities.size && size > queue.size) {
                sIndex = tmpIndex
                size = queue.size
            }
        }
        return intArrayOf(sIndex + 1, sIndex + size)
    }
}
