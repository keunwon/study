package com.keunwon.algorithm.againresolve

import kotlin.math.pow

class ALessons150367 {
    fun solution(numbers: LongArray): IntArray {
        return numbers.map {
            val binaryTree = createBinaryTree(it.toString(2))
            if (check(binaryTree)) 1 else 0
        }.toIntArray()
    }

    private fun check(binaryTree: String): Boolean {
        if (binaryTree.length == 1) return true

        val mid = binaryTree.length / 2
        if (binaryTree[mid] == '0') return binaryTree.all { it == '0' }
        return check(binaryTree.substring(0, mid)) && check(binaryTree.substring(mid + 1))
    }

    private fun createBinaryTree(binary: String): String {
        var n = 0
        var size = 1

        while (binary.length > size) {
            n++
            size += 2.0.pow(n).toInt()
        }
        return "0".repeat(size - binary.length) + binary
    }
}

fun main() {
    arrayOf(
        longArrayOf(7, 42, 5),
        longArrayOf(63, 111, 95),
    ).forEach { numbers ->
        ALessons150367().solution(numbers).also { println(it.contentToString()) }
    }
}
