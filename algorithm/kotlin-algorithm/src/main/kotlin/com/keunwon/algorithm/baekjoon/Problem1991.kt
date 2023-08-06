package com.keunwon.algorithm.baekjoon

/**
 * Title: 트리 순회
 * Level: 실버-1
 **/
class Problem1991 {
    private val answer = Array(3) { mutableListOf<Char>() }

    fun solution(arr: Array<Triple<Char, Char, Char>>): Array<String> {
        val root = arr[0].let { (target, left, right) ->
            Node(
                id = target,
                left = Node(left).takeUnless { left == '.' },
                right = Node(right).takeUnless { right == '.' },
            )
        }

        for (i in 1 until arr.size) {
            val (target, left, right) = arr[i]
            insert(root, target, left, right)
        }

        preOrder(root)
        inOrder(root)
        postOrder(root)
        return answer.map { it.joinToString("") }.toTypedArray()
    }

    private fun preOrder(node: Node?) {
        if (node == null) return

        answer[0].add(node.id)
        preOrder(node.left)
        preOrder(node.right)
    }

    private fun inOrder(node: Node?) {
        if (node == null) return

        inOrder(node.left)
        answer[1].add(node.id)
        inOrder(node.right)
    }

    private fun postOrder(node: Node?) {
        if (node == null) return

        postOrder(node.left)
        postOrder(node.right)
        answer[2].add(node.id)
    }

    private fun insert(node: Node, target: Char, left: Char, right: Char) {
        if (node.id == target) {
            node.left = Node(left).takeUnless { left == '.' }
            node.right = Node(right).takeUnless { right == '.' }
            return
        }
        node.left?.let { insert(it, target, left, right) }
        node.right?.let { insert(it, target, left, right) }
    }

    private data class Node(
        var id: Char,
        var left: Node? = null,
        var right: Node? = null,
    )
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!
            .replace(" ", "")
            .toCharArray()
            .let { (a, b, c) -> Triple(a, b, c) }
    }

    Problem1991().solution(arr).forEach(::println)
}
