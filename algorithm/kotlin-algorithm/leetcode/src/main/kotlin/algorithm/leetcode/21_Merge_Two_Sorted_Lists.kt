package algorithm.leetcode

class `21_Merge_Two_Sorted_Lists` {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        val result = ListNode(0)
        var cur = result
        var node1 = list1
        var node2 = list2

        while (node1 != null && node2 != null) {
            if (node1.`val` < node2.`val`) {
                cur.next = node1
                node1 = node1.next
            } else {
                cur.next = node2
                node2 = node2.next
            }
            cur = cur.next!!
        }

        if (node1 != null) cur.next = node1
        if (node2 != null) cur.next = node2
        return result.next
    }
}
