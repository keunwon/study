package com.keunwon.algorithm.programmers

class Lesson92334 {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val reportMap = report.distinct().map { it.split(" ") }
            .groupBy({ it[1] }, { it[0] })
            .filter { it.value.size >= k }
        val result = id_list.associateWith { 0 }.toMutableMap()

        for (id in id_list) {
            for ((_, list) in reportMap) {
                if (list.contains(id)) result[id] = result.getValue(id) + 1
            }
        }
        return result.values.toIntArray()
    }
}
