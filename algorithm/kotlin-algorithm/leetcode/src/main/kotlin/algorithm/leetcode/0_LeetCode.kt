package algorithm.leetcode

class `0_LeetCode` {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var cur = dummy
        var curHead: ListNode? = head

        repeat(n) { curHead = curHead?.next }

        while (curHead != null) {
            curHead = curHead.next
            cur = cur.next!!
        }
        cur.next = cur.next?.next
        return dummy.next
    }
}
