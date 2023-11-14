package com.keunwon.algorithm.againresolve

class ALessons72411 {
    private val map = mutableMapOf<String, Int>()

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val sortedOrders = orders.map { it.toCharArray().sorted().joinToString("") }
        val answer = mutableListOf<String>()

        for (size in course) {
            sortedOrders.forEach { order -> combination(size, "", order) }

            val max = map.maxOf { it.value }
            if (max < 2) continue

            map.onEach { (menu, count) -> if (count == max) answer.add(menu) }.clear()
        }
        return answer.sorted().toTypedArray()
    }

    private fun combination(depth: Int, menu: String, order: String) {
        if (depth == 0) {
            map[menu] = map.getOrDefault(menu, 0) + 1
            return
        }
        order.forEachIndexed { i, o ->
            combination(depth - 1, "$menu$o", order.substring(i + 1))
        }
    }
}

fun main() {
    ALessons72411().solution(
        arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"),
        intArrayOf(2, 3, 4)
    ).also { println(it.contentToString()) }

    ALessons72411().solution(
        arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"),
        intArrayOf(2, 3, 5)
    ).also { println(it.contentToString()) }

    ALessons72411().solution(
        arrayOf("XYZ", "XWY", "WXA"),
        intArrayOf(2, 3, 4)
    ).also { println(it.contentToString()) }
}
