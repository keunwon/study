package algorithm.leetcode

class `203_Remove_Linked_List_Elements` {
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var prev = dummy
        var cur = head

        while (cur != null) {
            if (cur.`val` == `val`) {
                prev.next = cur.next
            } else {
                prev = cur
            }
            cur = cur.next
        }
        return dummy.next
    }
}
