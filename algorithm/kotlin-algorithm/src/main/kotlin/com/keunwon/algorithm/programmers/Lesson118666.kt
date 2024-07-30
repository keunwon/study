package com.keunwon.algorithm.programmers

class Lesson118666 {
    fun solution(survey: Array<String>, choices: IntArray): String {
        val types = arrayOf(
            arrayOf('R', 'T'),
            arrayOf('C', 'F'),
            arrayOf('J', 'M'),
            arrayOf('A', 'N')
        )
        val points = intArrayOf(3, 2, 1, 0, 1, 2, 3)
        val map = mutableMapOf<Char, Int>()

        for ((su, n) in survey.zip(choices.toList())) {
            if (n == 4) continue

            val type = if (n < 4) su[0] else su[1]
            map[type] = map.getOrDefault(type, 0) + points[n - 1]
        }

        return types.joinToString("") { (a, b) ->
            if (map.getOrDefault(a, 0) >= map.getOrDefault(b, 0)) "$a" else "$b"
        }
    }
}
