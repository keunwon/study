package algorithm.leetcode

import java.util.PriorityQueue

class `215_Kth_Largest_Element_in_an_Array` {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val q = PriorityQueue<Int>(k)

        for (i in 0 until k) {
            q.offer(nums[i])
        }

        for (i in k until nums.size) {
            val num = nums[i]
            if (num > q.peek()) {
                q.poll()
                q.offer(num)
            }
        }
        return q.poll()
    }
}
