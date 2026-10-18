class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()

        var n = nums.size
        var res = mutableListOf<List<Int>>()

        for (i in 0 until n) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue
            }

            var l = i + 1
            var r = n - 1

            while (l < r) {
                var sum = nums[i] + nums[l] + nums[r]

                if (sum < 0) {
                    ++l
                } else if (sum > 0) {
                    --r
                } else {
                    res.add(listOf(nums[i], nums[l], nums[r]))
                    ++l
                    --r

                    while (l < r && nums[l] == nums[l - 1]) ++l
                    while (l < r && nums[r] == nums[r + 1]) --r
                }
            }
        }
        return res
    }
}