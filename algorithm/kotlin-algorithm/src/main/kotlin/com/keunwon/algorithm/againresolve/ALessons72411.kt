package com.keunwon.algorithm.againresolve

class ALessons72411 {
    private val map = mutableMapOf<String, Int>()

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val answer = mutableListOf<String>()
        val sortedOrders = orders.map { it.toCharArray().sorted().joinToString("") }

        course.forEach { size ->
            sortedOrders.forEach { order -> combination(size, 0, "", order) }
            val max = map.maxOf { it.value }
            if (max >= 2) {
                val keys = map.filter { it.value == max }.keys
                answer.addAll(keys)
                map.clear()
            }
        }
        return answer.sorted().toTypedArray()
    }

    private fun combination(depth: Int, startIndex: Int, cur: String, order: String) {
        if (depth == 0) {
            map[cur] = map.getOrDefault(cur, 0) + 1
            return
        }
        for (i in startIndex until order.length) {
            combination(depth - 1, i + 1, "$cur${order[i]}", order)
        }
    }
}

fun main() {
    val orders = arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH")
    val course = intArrayOf(2, 3, 4)
    ALessons72411().solution(orders, course).also { println(it.joinToString(", ")) }
}
