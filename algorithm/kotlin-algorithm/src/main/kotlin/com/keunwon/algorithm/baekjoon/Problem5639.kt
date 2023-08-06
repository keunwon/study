package com.keunwon.algorithm.baekjoon

/**
 * Title: 이진 검색 트리
 * Level: 골드-5
 **/
class Problem5639 {
    fun solution(arr: IntArray): String {
        val root = Node(arr[0])

        for (i in 1 until arr.size) insert(root, arr[i])
        return postOrder(root)
    }

    private fun postOrder(node: Node?): String {
        if (node == null) return ""
        return postOrder(node.left) + postOrder(node.right) + "${node.num}\n"
    }

    private fun insert(node: Node, n: Int) {
        if (n < node.num) {
            if (node.left == null) node.left = Node(n)
            else insert(node.left!!, n)
        } else {
            if (node.right == null) node.right = Node(n)
            else insert(node.right!!, n)
        }
    }

    private data class Node(
        var num: Int,
        var left: Node? = null,
        var right: Node? = null,
    )
}

fun main() {
    val arr = mutableListOf<Int>()
    while (true) {
        val n = readLine()
        if (n == null || n.isBlank()) break

        arr.add(n.toInt())
    }
    Problem5639().solution(arr.toIntArray()).also(::print)
}
