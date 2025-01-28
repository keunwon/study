package com.keunwon.algorithm.programmers

class Lesson92334_2 {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val reportMap = mutableMapOf<String, MutableSet<String>>()

        report.forEach { r ->
            val (u1, u2) = r.split(" ")

            if (!reportMap.contains(u2)) reportMap[u2] = HashSet(id_list.size)
            reportMap.getValue(u2).add(u1)
        }

        val idxMap = id_list.withIndex().associate { it.value to it.index }
        val result = IntArray(id_list.size)

        reportMap.forEach { (_, set) ->
            if (set.size >= k) {
                set.forEach { ++result[idxMap.getValue(it)] }
            }
        }

        return result
    }
}
