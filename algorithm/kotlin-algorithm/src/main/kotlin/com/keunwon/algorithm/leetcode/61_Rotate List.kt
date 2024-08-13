package com.keunwon.algorithm.leetcode

class `61_Rotate List` {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null) return null

        var cur = head
        var size = 0

        while (cur != null) {
            cur = cur.next
            ++size
        }

        val mod = k % size

        if (size == 1 || mod == 0) return head

        var prev: ListNode = head
        for (i in 0 until size - mod - 1) {
            prev = prev.next!!
        }

        var node = prev.next!!
        val result = node
        prev.next = null

        while (node.next != null) {
            node = node.next!!
        }
        node.next = head

        return result
    }
}

fun main() {
    `61_Rotate List`().rotateRight(listNodeOf(1, 2, 3, 4, 5), 2).also { it.printValues() } // 4,5,1,2,3
    `61_Rotate List`().rotateRight(listNodeOf(0, 1, 2), 4).also { it.printValues() } // 2, 0, 1
}
