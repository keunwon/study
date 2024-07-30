package com.keunwon.algorithm.programmers

class Lesson42892 {
    private val answer = Array(2) { mutableListOf<Int>() }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val nodes = nodeinfo.mapIndexed { index, (x, y) -> Node(index + 1, x, y) }
            .sortedWith(compareBy({ -it.y }, { it.x }))
        val root = nodes[0].apply {
            (1 until nodes.size).forEach { insert(this, nodes[it]) }
        }

        preOrder(root)
        postOrder(root)
        return answer.map { it.toIntArray() }.toTypedArray()
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

    private fun insert(parent: Node, child: Node) {
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child
            else insert(parent.left!!, child)
        } else if (child.x > parent.x) {
            if (parent.right == null) parent.right = child
            else insert(parent.right!!, child)
        }
    }

    private data class Node(
        val index: Int,
        val x: Int,
        val y: Int,
        var left: Node? = null,
        var right: Node? = null,
    )
}
