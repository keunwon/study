package com.keunwon.algorithm.leetcode

class `2_Add_Two_Numbers` {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var cur = dummy
        var node1 = l1
        var node2 = l2
        var carry = 0

        while (node1 != null || node2 != null || carry != 0) {
            val n1 = node1?.`val` ?: 0
            val n2 = node2?.`val` ?: 0

            val sum = n1 + n2 + carry
            val mod = sum % 10
            carry = sum / 10

            cur.next = ListNode(mod)
            cur = cur.next!!

            node1 = node1?.next
            node2 = node2?.next
        }
        return dummy.next
    }
}

fun main() {
    `2_Add_Two_Numbers`().addTwoNumbers(
        listNodeOf(9, 9, 9, 9, 9, 9, 9),
        listNodeOf(9, 9, 9, 9)
    ).also { it.printValues() } // 8,9,9,9,0,0,0,1
}
