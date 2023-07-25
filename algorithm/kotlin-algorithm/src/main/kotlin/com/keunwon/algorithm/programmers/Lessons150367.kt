package com.keunwon.algorithm.programmers

import kotlin.math.pow

/**
 * Title: 표현 가능한 이진트리
 * Level: 3
 **/
class Lessons150367 {
    fun solution(numbers: LongArray): IntArray {
        return numbers.map { number ->
            val binaryTree = binaryTree(number)
            if (check(binaryTree)) 1 else 0
        }.toIntArray()
    }

    private fun binaryTree(number: Long): String {
        val binary = number.toString(2)
        var n = 0
        var nodeSize = 1

        while (binary.length > nodeSize) {
            n++
            nodeSize += 2.0.pow(n).toInt()
        }
        return "0".repeat(nodeSize - binary.length) + binary
    }

    private fun check(binary: String): Boolean {
        if (binary.length == 1) return true

        val mid = binary.length / 2
        if (binary[mid] == '0') return binary.all { it == '0' }
        return check(binary.substring(0, mid)) && check(binary.substring(mid + 1))
    }
}
