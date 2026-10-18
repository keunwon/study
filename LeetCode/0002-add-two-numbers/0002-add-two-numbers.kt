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
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var head = dummy
        var cur1 = l1
        var cur2 = l2
        var carry = 0

        while (cur1 != null || cur2 != null || carry > 0) {
            val n1 = cur1?.`val` ?: 0
            val n2 = cur2?.`val` ?: 0
            val sum = n1 + n2 + carry

            head.next = ListNode(sum % 10)
            carry = sum / 10

            head = head.next!!
            cur1 = cur1?.next
            cur2 = cur2?.next
        }
        return dummy.next
    }
}