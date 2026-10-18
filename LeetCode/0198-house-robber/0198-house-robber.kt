class Solution {
    fun rob(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]
        else if (nums.size == 2) return maxOf(nums[0], nums[1])

        var one = nums[0]
        var two = maxOf(nums[0], nums[1])

        for (i in 2 until nums.size) {
            var next = maxOf(one + nums[i], two)
            one = two
            two = next
        }
        return two
    }
}