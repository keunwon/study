package com.keunwon.algorithm.leetcode

class `349_Intersection_of_Two_Arrays` {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val set = nums1.toSet()
        val result = mutableListOf<Int>()

        for (num in nums2) {
            if (set.contains(num)) result.add(num)
        }
        return result.toIntArray()
    }
}

fun main() {
    `349_Intersection_of_Two_Arrays`().intersection(
        intArrayOf(1, 2, 2, 1),
        intArrayOf(2, 2)
    ).also { println(it.joinToString(", ")) }
}
