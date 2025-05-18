package algorithm.programmers

import kotlin.math.abs

class Lesson181916 {
    fun solution(a: Int, b: Int, c: Int, d: Int): Int {
        val list = listOf(a, b, c, d)
            .groupingBy { it }
            .eachCount().entries
            .sortedByDescending { it.value }

        return when (list.size) {
            1 -> list[0].key * 1111

            2 -> {
                val p = list[0]
                val q = list[1]

                if (p.value == 3 && q.value == 1) {
                    val n = 10 * p.key + q.key
                    n * n
                } else if (p.value == 2 && q.value == 2) {
                    (p.key + q.key) * abs(p.key - q.key)
                } else {
                    -1
                }
            }

            3 -> {
                val q = list[1]
                val r = list[2]
                q.key * r.key
            }

            else -> list.minOf { it.key }
        }
    }
}
