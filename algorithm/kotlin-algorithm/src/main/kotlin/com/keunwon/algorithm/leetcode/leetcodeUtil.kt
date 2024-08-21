package com.keunwon.algorithm.leetcode

import java.util.*

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TreeNode

        return `val` == other.`val`
    }

    override fun hashCode(): Int {
        return `val`
    }
}

class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}

fun listTreeNodeOf(vararg numbers: Int?): TreeNode? {
    if (numbers.isEmpty()) return null

    val root = TreeNode(numbers[0]!!)
    val queue = LinkedList<TreeNode>().apply { push(root) }
    var index = 1

    while (index < numbers.size) {
        val cur = queue.poll() ?: continue

        numbers[index]?.let { cur.left = TreeNode(it) }
        queue.offer(cur.left)
        ++index

        if (index < numbers.size) {
            numbers[index]?.let { cur.right = TreeNode(it) }
            queue.offer(cur.right)
            ++index
        }
    }
    return root
}

fun listNodeOf(vararg numbers: Int): ListNode {
    val nodes = numbers.map { ListNode(it) }
    for (i in 1 until nodes.size) {
        nodes[i - 1].next = nodes[i]
    }
    return nodes[0]
}

fun TreeNode?.printValues() {
    if (this == null) {
        println("[]")
        return
    }

    val result = mutableListOf<Int?>()
    val q = LinkedList<TreeNode?>()
    q.offer(this)

    while (q.isNotEmpty()) {
        val cur = q.poll()
        result.add(cur?.`val`)

        if (cur?.left == null && cur?.right == null) continue

        q.offer(cur.left)
        q.offer(cur.right)
    }
    println(result.joinToString(",", "[", "]"))
}

fun ListNode?.printValues() {
    if (this == null) {
        println("ListNode is null")
        return
    }

    val sb = StringBuilder()
    var cur = this

    while (cur != null) {
        sb.append(cur.`val`).append(", ")
        cur = cur.next
    }
    println(sb.toString())
}
