package algorithm.leetcode

class `234_Palindrome_Linked_List` {
    fun isPalindrome(head: ListNode?): Boolean {
        if (head == null) return false

        val numbers = mutableListOf<Int>()
        var cur = head

        while (cur != null) {
            numbers.add(cur.`val`)
            cur = cur.next
        }

        var l = 0
        var r = numbers.lastIndex
        while (l <= r) {
            if (numbers[l++] != numbers[r--]) return false
        }
        return true
    }
}
