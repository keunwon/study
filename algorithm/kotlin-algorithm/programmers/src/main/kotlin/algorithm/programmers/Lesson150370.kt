package algorithm.programmers

class Lesson150370 {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val target = today.toDay()
        val termMap = terms.map { it.split(" ") }.associate { it[0] to it[1].toInt() }

        return privacies.mapIndexedNotNull { index, privacy ->
            val (date, type) = privacy.split(" ")
            val day = date.toDay() + termMap.getValue(type)
            if (target >= day) index + 1 else null
        }.toIntArray()
    }

    private fun String.toDay(): Int {
        val (y, m, d) = split(".").map { it.toInt() }
        return (y * 28 * 12) + ((m - 1) * 28) + d
    }
}
