class Solution {
    fun rotate(nums: IntArray, k: Int): Unit {
        var t = k % nums.size

        reverse(nums, 0, nums.lastIndex)
        reverse(nums, 0, t - 1)
        reverse(nums, t, nums.lastIndex)
    }

    private fun reverse(nums: IntArray, left: Int, right: Int) {
        var l = left
        var r = right

        while (l < r) {
            val tmp = nums[l]
            nums[l++] = nums[r]
            nums[r--] = tmp;
        }
    }
}