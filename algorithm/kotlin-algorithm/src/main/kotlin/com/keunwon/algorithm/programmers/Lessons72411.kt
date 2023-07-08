package com.keunwon.algorithm.programmers

/**
 * Title: 메뉴 리뉴얼
 * Level: 2
 **/
class Lessons72411 {
    private val map = mutableMapOf<String, Int>()

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val sortedOrders = orders.map { it.toCharArray().sorted().joinToString("") }
        val answer = mutableListOf<String>()

        for (size in course) {
            sortedOrders.forEach { order -> combination(size, "", order) }

            val max = map.maxOf { it.value }
            if (max <= 1) continue

            map.onEach { (menu, count) -> if (count == max) answer.add(menu) }.clear()
        }
        return answer.sorted().toTypedArray()
    }

    private fun combination(depth: Int, str: String, order: String) {
        if (depth == 0) {
            map[str] = map.getOrDefault(str, 0) + 1
            return
        }

        order.forEachIndexed { index, w ->
            combination(depth - 1, str + w, order.substring(index + 1))
        }
    }
}
