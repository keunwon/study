package algorithm.leetcode

class `15_3Sum` {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = hashSetOf<List<Int>>()
        nums.sort()

        for (i in 0 until nums.size - 2) {
            var left = i + 1
            var right = nums.lastIndex

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]
                if (sum == 0) {
                    result.add(listOf(nums[i], nums[left], nums[right]))
                    ++left
                    --right
                } else if (sum < 0) {
                    ++left
                } else {
                    --right
                }
            }
        }
        return result.toList()
    }
}
