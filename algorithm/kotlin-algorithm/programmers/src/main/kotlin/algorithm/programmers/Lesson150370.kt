package algorithm.programmers

class Lesson150370 {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val target = today.toDays()
        val termMap = terms.map { it.split(" ") }.associate { it[0] to it[1].toInt() }
        val result = mutableListOf<Int>()

        for ((index, p) in privacies.withIndex()) {
            val days = p.split(" ").let { it[0].toDays() + termMap.getValue(it[1]) * 28 }
            if (days <= target) result.add(index + 1)
        }
        return result.toIntArray()
    }

    private fun String.toDays(): Int {
        val (y, m, d) = split(".").map { it.toInt() }
        return (y * 28 * 12) + (m * 28) + d
    }
}
