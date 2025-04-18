package algorithm.leetcode

import java.util.PriorityQueue

class `2542_Maximum_Subsequence_Score` {
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        val list = nums1.zip(nums2).sortedByDescending { it.second }
        val q = PriorityQueue<Int>()
        var sum = 0L
        var result = 0L

        for ((a, b) in list) {
            sum += a
            q.offer(a)

            if (q.size > k) sum -= q.poll()
            if (q.size == k) result = result.coerceAtLeast(sum * b)
        }
        return result
    }
}

fun main() {
    `2542_Maximum_Subsequence_Score`()
        .maxScore(intArrayOf(1, 3, 3, 2), intArrayOf(2, 1, 3, 4), 3)
        .also { println(it) } // 12

    `2542_Maximum_Subsequence_Score`()
        .maxScore(intArrayOf(4, 2, 3, 1, 1), intArrayOf(7, 5, 10, 9, 6), 1)
        .also { println(it) } // 30
}
