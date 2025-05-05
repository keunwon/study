package algorithm.leetcode

class `1290_Convert_Binary_Number_in_a_Linked_List_to_Integer` {
    fun getDecimalValue(head: ListNode?): Int {
        if (head == null) return 0

        var cur = head
        var sb = StringBuilder()

        while (cur != null) {
            sb.append(cur.`val`)
            cur = cur.next
        }
        return sb.toString().toInt(2)
    }
}
