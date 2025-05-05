package algorithm.leetcode

class `674_Longest_Continuous_Increasing_Subsequence` {
    fun findLengthOfLCIS(nums: IntArray): Int {
        var pre = nums[0]
        var result = 0
        var count = 1

        for (num in nums) {
            if (pre < num) {
                ++count
            } else {
                result = result.coerceAtLeast(count)
                count = 1
            }
            pre = num
        }
        result = result.coerceAtLeast(count)
        return result
    }
}

fun main() {
    `674_Longest_Continuous_Increasing_Subsequence`().findLengthOfLCIS(intArrayOf(1, 3, 5, 7))
        .also { println(it) } // 4
}
