package com.keunwon.algorithm.leetcode

class `92_Reverse_Linked_List_II` {
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var prev = dummy

        repeat(left - 1) { prev = prev.next!! }

        val cur = prev.next!!
        for (i in 0 until right - left) {
            val next = cur.next!!
            cur.next = next.next
            next.next = prev.next
            prev.next = next
        }
        return dummy.next
    }
}

fun main() {
    `92_Reverse_Linked_List_II`()
        .reverseBetween(listNodeOf(1, 2, 3, 4, 5), 2, 4)
        .also { it.printValues() } // 1,4,3,2,5
}
