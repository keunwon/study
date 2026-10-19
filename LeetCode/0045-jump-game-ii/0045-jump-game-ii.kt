class Solution {
    fun jump(nums: IntArray): Int {
        if (nums.size == 1) return 0
        
        var idx = 0
        var res = 0
        var max = 0

        for ((i, n) in nums.withIndex()) {
            max = maxOf(max, i + n)

            if (idx == i) {
                idx = max
                ++res
                if (idx >= nums.lastIndex) break
            }
        }
        return res
    }
}