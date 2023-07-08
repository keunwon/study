package com.keunwon.algorithm.programmers

/**
 * Title: 성격 유형 검사하기
 * Level: 1
 **/
// todo
class Lessons118666 {
    private val question = mapOf(1 to 3, 2 to 2, 3 to 1, 4 to 0, 5 to 1, 6 to 2, 7 to 3)

    fun solution(survey: Array<String>, choices: IntArray): String {
        val map = mutableMapOf<String, Int>()
        for (i in survey.indices) {
            val (type1, type2) = survey[i].map { it.toString() }
            val score = question.getValue(choices[i])

            if (choices[i] < 4) {
                map[type1] = map.getOrDefault(type1, 0) + score
            } else if (choices[i] > 4) {
                map[type2] = map.getOrDefault(type2, 0) + score
            }
        }

        val types = arrayOf("R" to "T", "C" to "F", "J" to "M", "A" to "N")
        return types.joinToString("") { (type1, type2) ->
            val num1 = map.getOrDefault(type1, 0)
            val num2 = map.getOrDefault(type2, 0)
            if (num1 >= num2) type1 else type2
        }
    }
}
