package algorithm.leetcode

class `88_Merge_Sorted_Array` {
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        for (i in 0 until n) {
            nums1[i + m] = nums2[i]
        }
        nums1.sort()
    }
}
