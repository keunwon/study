package com.keunwon.algorithm.programmers

class Lesson72412 {
    private val search = mutableMapOf<String, MutableList<Int>>()

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        info.forEach { combination(0, "", it.split(" ")) }
        search.values.forEach { it.sort() }

        return IntArray(query.size) {
            val (key, point) = query[it].replace(" and ", "").split(" ")
            binarySearch(key, point.toInt())
        }
    }

    private fun binarySearch(key: String, point: Int): Int {
        val list = search[key] ?: return 0
        var left = 0
        var right = list.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            if (point <= list[mid]) right = mid - 1
            else left = mid + 1
        }
        return list.size - left
    }

    private fun combination(depth: Int, key: String, info: List<String>) {
        if (depth == info.lastIndex) {
            val point = info.last().toInt()

            if (search.containsKey(key)) {
                search.getValue(key).add(point)
            } else {
                search[key] = mutableListOf(point)
            }
            return
        }

        combination(depth + 1, "$key-", info)
        combination(depth + 1, "$key${info[depth]}", info)
    }
}
