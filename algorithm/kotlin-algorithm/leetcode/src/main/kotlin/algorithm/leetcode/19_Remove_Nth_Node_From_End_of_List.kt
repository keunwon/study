package algorithm.leetcode

class `19_Remove_Nth_Node_From_End_of_List` {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val dummy = ListNode(0).apply { next = head }
        var cur = dummy
        var curHead: ListNode? = head

        repeat(n) { curHead = curHead?.next }

        while (curHead != null) {
            curHead = curHead!!.next
            cur = cur.next!!
        }
        cur.next = cur.next?.next
        return dummy.next
    }
}

fun main() {
    `19_Remove_Nth_Node_From_End_of_List`().removeNthFromEnd(
        listNodeOf(1, 2, 3, 4, 5),
        2
    ).also { it.printValues() }
}
