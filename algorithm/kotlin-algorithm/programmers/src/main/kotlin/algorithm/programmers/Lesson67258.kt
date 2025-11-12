package algorithm.programmers

class Lesson67258 {
    fun solution(gems: Array<String>): IntArray {
        val types = gems.toSet()
        val quantities = mutableMapOf<String, Int>()
        var startIndex = 0
        val result = intArrayOf(0, gems.lastIndex)

        for ((i, gem) in gems.withIndex()) {
            quantities[gem] = quantities.getOrDefault(gem, 0) + 1

            while (startIndex < gems.size && quantities.getValue(gems[startIndex]) > 1) {
                quantities[gems[startIndex]] = quantities.getValue(gems[startIndex]) - 1
                ++startIndex
            }

            if (types.size == quantities.size) {
                if (result[1] - result[0] > i - startIndex) {
                    result[0] = startIndex
                    result[1] = i
                }
            }
        }

        return IntArray(2) { result[it] + 1 }
    }
}
