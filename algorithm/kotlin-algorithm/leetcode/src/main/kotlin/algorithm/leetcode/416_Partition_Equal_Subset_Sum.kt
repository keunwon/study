package algorithm.leetcode

class `416_Partition_Equal_Subset_Sum` {
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 != 0) return false

        val target = sum / 2
        val dp = BooleanArray(target + 1).apply { this[0] = true }

        for (num in nums) {
            for (i in target downTo num) {
                dp[i] = dp[i] || dp[i - num]
            }
        }
        return dp.last()
    }
}

fun main() {
    `416_Partition_Equal_Subset_Sum`().canPartition(intArrayOf(1, 5, 11, 5)).also { println(it) } // true
    `416_Partition_Equal_Subset_Sum`().canPartition(intArrayOf(1, 2, 5)).also { println(it) } // false
    `416_Partition_Equal_Subset_Sum`().canPartition(intArrayOf(3, 3, 3, 4, 5)).also { println(it) } // true
}
