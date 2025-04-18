package algorithm.leetcode

class `53_Maximum_Subarray` {
    fun maxSubArray(nums: IntArray): Int {
        var result = 0
        var total = 0

        for (num in nums) {
            if (total < 0) total = 0

            total += num
            result = result.coerceAtLeast(total)
        }
        return result
    }
}

fun main() {
    `53_Maximum_Subarray`().maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))
        .also { println(it) } // 6

    `53_Maximum_Subarray`().maxSubArray(intArrayOf(1))
        .also { println(it) } // 1

    `53_Maximum_Subarray`().maxSubArray(intArrayOf(5, 4, -1, 7, 8))
        .also { println(it) } // 23
}
