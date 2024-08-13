package com.keunwon.algorithm.leetcode

class `86_Partition_List` {
    fun partition(head: ListNode?, x: Int): ListNode? {
        val dummyLeft = ListNode(-2000)
        val dummyRight = ListNode(-2000)
        var left = dummyLeft
        var right = dummyRight
        var cur = head

        while (cur != null) {
            if (cur.`val` < x) {
                left.next = cur
                left = cur
            } else {
                right.next = cur
                right = cur
            }
            cur = cur.next
        }

        right.next = null
        left.next = dummyRight.next
        return dummyLeft.next
    }
}

fun main() {
    `86_Partition_List`().partition(listNodeOf(1, 4, 3, 2, 5, 2), 3).also { it.printValues() } // 1,2,2,4,3,5
    `86_Partition_List`().partition(listNodeOf(2, 1), 2).also { it.printValues() } // 1, 2
}
