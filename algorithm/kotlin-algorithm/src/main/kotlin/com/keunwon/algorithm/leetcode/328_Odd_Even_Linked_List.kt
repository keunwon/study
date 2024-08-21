package com.keunwon.algorithm.leetcode

class `328_Odd_Even_Linked_List` {
    fun oddEvenList(head: ListNode?): ListNode? {
        if (head == null) return null

        var odd = head
        var even = head.next
        val evenHead = head.next

        while (even?.next != null) {
            odd!!.next = odd.next?.next
            even.next = even.next?.next

            odd = odd.next
            even = even.next
        }
        odd!!.next = evenHead
        return head
    }
}

fun main() {
    `328_Odd_Even_Linked_List`().oddEvenList(
        listNodeOf(1, 2, 3, 4, 5)
    ).also { it.printValues() }
}
