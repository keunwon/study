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
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var head = dummy
        var cur1 = list1
        var cur2 = list2

        while (cur1 != null && cur2 != null) {
            if (cur1.`val` < cur2.`val`) {
                head.next = cur1
                cur1 = cur1.next
            } else {
                head.next = cur2
                cur2 = cur2.next
            }
            head = head.next!!
        }

        if (cur1 != null) {
            head.next = cur1
        } else {
            head.next = cur2
        }
        
        return dummy.next
    }
}