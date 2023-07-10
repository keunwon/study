package com.keunwon.algorithm.baekjoon

/**
 * Title: 암기왕
 * Level: 실버-4
 **/
class Problem2776 {
    fun solution(note1: IntArray, note2: IntArray): IntArray {
        note1.sort()
        return note2.map { binarySearch(note1, it) }.toIntArray()
    }

    private fun binarySearch(note: IntArray, x: Int): Int {
        var left = 0
        var right = note.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2

            when {
                x == note[mid] -> return 1
                x < note[mid] -> right = mid - 1
                else -> left = mid + 1
            }
        }
        return 0
    }
}

fun main() {
    val createArray = {
        readLine()!!
        readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    }
    val n = readLine()!!.toInt()

    repeat(n) {
        val note1 = createArray()
        val note2 = createArray()
        Problem2776().solution(note1, note2).also { println(it.joinToString("\n")) }
    }
}
