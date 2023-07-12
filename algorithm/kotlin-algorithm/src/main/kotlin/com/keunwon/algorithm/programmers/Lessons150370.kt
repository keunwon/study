package com.keunwon.algorithm.programmers

/**
 * Title: 개인정보 수집 유효기간
 * Level: 1
 **/
class Lessons150370 {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val rules = terms.associate { term ->
            val (type, month) = term.split(" ")
            type to month.toInt() * 28
        }
        val targetDay = getDay(today)
        val destructionDays = privacies.mapIndexed { index, p ->
            val (time, type) = p.split(" ")
            val day = getDay(time) + rules.getValue(type)
            index + 1 to day
        }

        return destructionDays.filter { (_, day) -> day <= targetDay }
            .map { it.first }.toIntArray()
    }

    private fun getDay(time: String): Int {
        val (year, month, day) = time.split(".").map { it.toInt() }
        return day + month * 28 + (year - 2000) * 12 * 28
    }
}
