package com.keunwon.algorithm.programmers

/**
 * Title: 신고 결과 받기
 * Level: 1
 **/
class Lessons92334 {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val userMap = id_list.associateWith { mutableSetOf<String>() }
        val reportMap = id_list.associateWith { 0 }.toMutableMap()

        report.forEach { r ->
            val (p1, p2) = r.split(" ")

            if (!userMap.getValue(p1).contains(p2)) {
                userMap.getValue(p1).add(p2)
                reportMap[p2] = reportMap.getOrDefault(p2, 0) + 1
            }
        }

        val bannedList = reportMap.filter { it.value >= k }
        return id_list.map { id -> userMap.getValue(id).count { it in bannedList } }.toIntArray()
    }
}
