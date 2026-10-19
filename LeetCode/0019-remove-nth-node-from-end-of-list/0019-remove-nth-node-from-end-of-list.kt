/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var slow = dummy
        var fast = head

        repeat(n) { fast = fast?.next }

        while (fast != null) {
            slow = slow.next!!
            fast = fast.next
        }

        slow.next = slow.next?.next
        return dummy.next
    }
}