package algorithm.leetcode

class `2095_Delete_the_Middle_Node_of_a_Linked_List` {
    fun deleteMiddle(head: ListNode?): ListNode? {
        if (head?.next == null) return null

        var slow = head
        var fast = head.next!!.next

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        slow?.next = slow?.next?.next

        return head
    }
}

fun main() {
    `2095_Delete_the_Middle_Node_of_a_Linked_List`().deleteMiddle(
        listNodeOf(1, 3, 4, 7, 1, 2, 6)
    ).also { it.printValues() } // 1,3,4,1,2,6
}
