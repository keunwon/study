package algorithm.leetcode

class `83_Remove_Duplicates_from_Sorted_List` {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        var cur = head
        while (cur?.next != null) {
            if (cur.`val` == cur.next!!.`val`) {
                cur.next = cur.next!!.next
            } else {
                cur = cur.next
            }
        }
        return head
    }
}
