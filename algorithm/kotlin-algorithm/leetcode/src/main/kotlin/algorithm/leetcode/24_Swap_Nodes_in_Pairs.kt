package algorithm.leetcode

class `24_Swap_Nodes_in_Pairs` {
    fun swapPairs(head: ListNode?): ListNode? {
        var cur = head
        while (cur?.next != null) {
            val n1 = cur.`val`
            val n2 = cur.next!!.`val`

            cur.`val` = n2
            cur.next!!.`val` = n1
            cur = cur.next!!.next
        }
        return head
    }
}
