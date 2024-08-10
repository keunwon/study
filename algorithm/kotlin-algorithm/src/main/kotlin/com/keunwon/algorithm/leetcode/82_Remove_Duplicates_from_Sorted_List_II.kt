package com.keunwon.algorithm.leetcode

class `82_Remove_Duplicates_from_Sorted_List_II` {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var prev: ListNode? = dummy
        var cur: ListNode? = head

        while (cur != null) {
            while (cur!!.next != null && cur.`val` == cur.next!!.`val`) {
                cur = cur.next
            }

            if (prev!!.next === cur) {
                prev = prev!!.next
            } else {
                prev!!.next = cur.next
            }
            cur = cur.next
        }
        return dummy.next
    }
}

fun main() {
    `82_Remove_Duplicates_from_Sorted_List_II`()
        .deleteDuplicates(listNodeOf(1, 2, 3, 3, 4, 4, 5))
        .also { it.printValues() }
}
