package com.keunwon.algorithm.leetcode

import java.util.*
import kotlin.math.min

class `373_Find_K_Pairs_with_Smallest_Sums` {
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        val q = PriorityQueue<IntArray>(compareBy { it[0] + it[1] }).apply {
            repeat(min(nums1.size, k)) { offer(intArrayOf(nums1[it], nums2[0], 0)) }
        }
        var kk = k
        val result = mutableListOf<List<Int>>()

        while (q.isNotEmpty() && kk-- > 0) {
            val cur = q.poll()
            val nextIdx = cur[2] + 1

            result.add(listOf(cur[0], cur[1]))
            if (nextIdx < nums1.size) {
                q.offer(intArrayOf(cur[0], nums2[nextIdx], nextIdx))
            }
        }
        return result
    }
}

fun main() {
    `373_Find_K_Pairs_with_Smallest_Sums`()
        .kSmallestPairs(intArrayOf(1, 7, 11), intArrayOf(2, 4, 6), 3)
        .also { it.printlnValues() } // [[1,2],[1,4],[1,6]]

    `373_Find_K_Pairs_with_Smallest_Sums`()
        .kSmallestPairs(intArrayOf(1, 1, 2), intArrayOf(1, 2, 3), 2)
        .also { it.printlnValues() } // [[1,1],[1,1]]
}
