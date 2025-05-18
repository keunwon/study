package algorithm.programmers

import kotlin.math.sqrt

class Lesson12977 {
    fun solution(nums: IntArray): Int {
        val compositeNumbers = BooleanArray(3001).apply {
            for (i in 2..sqrt(size.toDouble()).toInt()) {
                for (j in i + i until size step i) {
                    this[j] = true
                }
            }
        }
        var result = 0

        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                for (k in j + 1 until nums.size) {
                    val sum = nums[i] + nums[j] + nums[k]
                    if (!compositeNumbers[sum]) ++result
                }
            }
        }
        return result
    }
}
