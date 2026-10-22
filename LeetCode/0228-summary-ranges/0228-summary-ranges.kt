class Solution {
    fun summaryRanges(nums: IntArray): List<String> {
        val res = mutableListOf<String>()
        var idx = 0

        while (idx < nums.size) {
            val sIdx = idx
            while (idx + 1 < nums.size && nums[idx + 1] - nums[idx] == 1) {
                ++idx
            }

            res.add(
                if (sIdx == idx) "${nums[idx]}"
                else "${nums[sIdx]}->${nums[idx]}"
            )
            ++idx
        }
        return res
    }
}