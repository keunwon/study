package com.keunwon.algorithm.leetcode

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Node(var `val`: Int) {
    var next: Node? = null
    var random: Node? = null
}

fun listNodeOf(vararg numbers: Int): ListNode {
    val nodes = numbers.map { ListNode(it) }
    for (i in 1 until nodes.size) {
        nodes[i - 1].next = nodes[i]
    }
    return nodes[0]
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
