package com.keunwon.algorithm.againresolve

class ALessons72412 {
    private val map = mutableMapOf<String, MutableList<Int>>()

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        info.forEach { combination(0, "", it.split(" ")) }
        map.values.forEach { it.sort() }

        return query.map {
            val (key, point) = it.replace(" and ", "").split(" ")
            lowerBound(key, point.toInt())
        }.toIntArray()
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

    private fun combination(index: Int, key: String, list: List<String>) {
        if (index == list.lastIndex) {
            val point = list.last().toInt()
            map.compute(key) { _, value -> value?.apply { add(point) } ?: mutableListOf(point) }
            return
        }
        combination(index + 1, "$key-", list)
        combination(index + 1, "$key${list[index]}", list)
    }
}

fun main() {
    ALessons72412().solution(
        arrayOf(
            "java backend junior pizza 150",
            "python frontend senior chicken 210",
            "python frontend senior chicken 150",
            "cpp backend senior pizza 260",
            "java backend junior chicken 80",
            "python backend senior chicken 50"
        ),
        arrayOf(
            "java and backend and junior and pizza 100",
            "python and frontend and senior and chicken 200",
            "cpp and - and senior and pizza 250",
            "- and backend and senior and - 150",
            "- and - and - and chicken 100",
            "- and - and - and - 150"
        )
    ).also { println(it.contentToString()) }
}
