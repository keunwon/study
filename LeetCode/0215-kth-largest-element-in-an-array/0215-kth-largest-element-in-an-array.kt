class Solution {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val count = IntArray(20001)
        for (n in nums) {
            ++count[n + 10000]
        }

        var ret = k
        for (i in count.lastIndex downTo 0) {
            ret -= count[i]
            if (ret <= 0) return i - 10000
        }
        return -1
    }
}