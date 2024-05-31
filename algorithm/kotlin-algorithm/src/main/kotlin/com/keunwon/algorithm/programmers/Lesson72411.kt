package com.keunwon.algorithm.programmers

class Lesson72411 {
    private val words = mutableListOf<String>()

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val answer = mutableListOf<String>()
        val sortedOrders = orders.map { it.toCharArray().sorted().joinToString("") }

        for (size in course) {
            sortedOrders.forEach { order -> permutation(size, "", order, 0) }

            val countMap = words.groupingBy { it }.eachCount()
            val max = countMap.maxOfOrNull { it.value } ?: 0

            if (max > 1) {
                countMap.forEach { (key, value) -> if (value == max) answer.add(key) }
            }
            words.clear()
        }
        return answer.sorted().toTypedArray()
    }

    private fun permutation(size: Int, str: String, order: String, cur: Int) {
        if (size == 0) {
            words.add(str)
            return
        }

        for (i in cur until order.length) {
            permutation(size - 1, "$str${order[i]}", order, i + 1)
        }
    }
}