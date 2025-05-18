package algorithm.programmers

class Lesson131127 {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        var result = 0
        val target = want.zip(number.toList())
        val quantities = mutableMapOf<String, Int>()

        for (i in 0 until 10) {
            val str = discount[i]
            quantities[str] = quantities.getOrDefault(str, 0) + 1
        }

        if (target.size == quantities.size && target.all { it.second == quantities.getOrDefault(it.first, 0) }) {
            ++result
        }

        for (i in 10 until discount.size) {
            val key = discount[i]
            val prevKey = discount[i - 10]

            quantities[key] = quantities.getOrDefault(key, 0) + 1
            quantities[prevKey] = quantities.getValue(prevKey) - 1
            if (quantities.getValue(prevKey) == 0) quantities.remove(prevKey)

            if (target.size == quantities.size && target.all { it.second == quantities.getOrDefault(it.first, 0) }) {
                ++result
            }
        }

        return result
    }
}
