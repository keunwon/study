package com.keunwon.algorithm.leetcode

class `108_Convert_Sorted_Array_to_Binary_Search_Tree` {
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        if (nums.isEmpty()) return null
        return dfs(nums, 0, nums.lastIndex)
    }

    private fun dfs(nums: IntArray, left: Int, right: Int): TreeNode? {
        if (left > right) return null

        val mid = (left + right) / 2
        return TreeNode(nums[mid]).apply {
            this.left = dfs(nums, left, mid - 1)
            this.right = dfs(nums, mid + 1, right)
        }
    }
}
