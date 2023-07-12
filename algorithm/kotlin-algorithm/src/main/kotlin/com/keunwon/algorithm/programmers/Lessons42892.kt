package com.keunwon.algorithm.programmers

/**
 * Title: 길 찾기 게임
 * Level: 42892
 **/
class Lessons42892 {
    private val answer = Array(2) { mutableListOf<Int>() }

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val nodes = nodeinfo.mapIndexed { index, (x, y) -> Node(index + 1, x, y) }
            .sortedWith(compareBy({ -it.y }, { it.x }))

        with(nodes.first()) {
            for (i in 1 until nodes.size) insert(this, nodes[i])

            preOrder(this)
            postOrder(this)
        }
        return answer.map { it.toIntArray() }.toTypedArray()
    }

    private fun preOrder(parent: Node?) {
        if (parent == null) return

        answer[0].add(parent.idx)
        preOrder(parent.left)
        preOrder(parent.right)
    }

    private fun postOrder(parent: Node?) {
        if (parent == null) return

        postOrder(parent.left)
        postOrder(parent.right)
        answer[1].add(parent.idx)
    }

    private fun insert(parent: Node, child: Node) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child
            else insert(parent.left!!, child)
        } else {
            if (parent.right == null) parent.right = child
            else insert(parent.right!!, child)
        }
    }

    data class Node(
        var idx: Int,
        val x: Int,
        val y: Int,
        var left: Node? = null,
        var right: Node? = null,
    )
}
