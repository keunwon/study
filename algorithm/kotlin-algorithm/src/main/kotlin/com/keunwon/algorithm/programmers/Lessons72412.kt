package com.keunwon.algorithm.programmers

/**
 * Title: 순위 검색
 * Level: 2
 **/
class Lessons72412 {
    private val map = mutableMapOf<String, MutableList<Int>>()

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        info.forEach { str -> comb(0, "", str.split(" ").toTypedArray()) }
        map.values.forEach { it.sort() }

        val answer = IntArray(query.size)
        for ((i, str) in query.withIndex()) {
            val (key, score) = str.replace(" and ", "").split(" ")
            answer[i] = lowerBound(key, score.toInt())
        }
        return answer
    }

    private fun lowerBound(key: String, score: Int): Int {
        if (!map.containsKey(key)) return 0

        val target = map.getValue(key)
        var left = 0
        var right = target.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2
            if (score <= target[mid]) right = mid - 1
            else left = mid + 1
        }
        return target.size - left
    }

    private fun comb(depth: Int, key: String, info: Array<String>) {
        if (depth == 4) {
            val score = info.last().toInt()
            if (map.containsKey(key)) map.getValue(key).add(score)
            else map[key] = mutableListOf(score)
            return
        }
        comb(depth + 1, "$key-", info)
        comb(depth + 1, "$key${info[depth]}", info)
    }
}
