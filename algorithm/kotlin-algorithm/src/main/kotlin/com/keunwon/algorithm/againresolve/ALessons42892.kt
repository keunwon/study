package com.keunwon.algorithm.againresolve

class ALessons42892 {
    private val answer = Array(2) { mutableListOf<Int>() }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val binaryTree = nodeinfo.mapIndexed { i, (x, y) -> Node(i + 1, x, y) }
            .sortedWith(compareBy({ -it.y }, { it.x }))
        val root = binaryTree.first()

        for (i in 1 until binaryTree.size) insert(root, binaryTree[i])

        preOrder(root)
        postOrder(root)
        return answer.map { it.toIntArray() }.toTypedArray()
    }

    private fun insert(parent: Node, child: Node) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child
            else insert(parent.left!!, child)
        } else if (parent.x < child.x) {
            if (parent.right == null) parent.right = child
            else insert(parent.right!!, child)
        }
    }

    private fun preOrder(node: Node?) {
        if (node == null) return

        answer[0].add(node.index)
        preOrder(node.left)
        preOrder(node.right)
    }

    private fun postOrder(node: Node?) {
        if (node == null) return

        postOrder(node.left)
        postOrder(node.right)
        answer[1].add(node.index)
    }

    private data class Node(
        val index: Int,
        val x: Int,
        val y: Int,
        var left: Node? = null,
        var right: Node? = null,
    )
}

fun main() {
    val nodeInfo = arrayOf(
        intArrayOf(5, 3),
        intArrayOf(11, 5),
        intArrayOf(13, 3),
        intArrayOf(3, 5),
        intArrayOf(6, 1),
        intArrayOf(1, 3),
        intArrayOf(8, 6),
        intArrayOf(7, 2),
        intArrayOf(2, 2),
    )
    ALessons42892().solution(nodeInfo).forEach { println(it.joinToString(", ")) }
}
