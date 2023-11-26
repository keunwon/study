package com.keunwon.algorithm.againresolve

import kotlin.math.pow

class ALessons150367 {
    fun solution(numbers: LongArray): IntArray {
        return numbers.map {
            val binaryTree = binaryTree(it)
            if (check(binaryTree)) 1 else 0
        }.toIntArray()
    }

    private fun binaryTree(number: Long): String {
        val binary = number.toString(2)
        var depth = 0
        var nodeSize = 1

        while (binary.length > nodeSize) {
            depth++
            nodeSize += 2.0.pow(depth).toInt()
        }
        return "0".repeat(nodeSize - binary.length) + binary
    }

    private fun check(binaryTree: String): Boolean {
        if (binaryTree.length == 1) return true

        val mid = binaryTree.length / 2
        if (binaryTree[mid] == '0') return binaryTree.all { it == '0' }
        return check(binaryTree.substring(0, mid)) && check(binaryTree.substring(mid + 1))
    }
}

fun main() {
    ALessons150367().solution(
        longArrayOf(7, 42, 5)
    ).also { println(it.contentToString()) }

    ALessons150367().solution(
        longArrayOf(63, 111, 95)
    ).also { println(it.contentToString()) }
}
