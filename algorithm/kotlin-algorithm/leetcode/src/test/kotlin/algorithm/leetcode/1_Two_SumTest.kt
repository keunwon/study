package algorithm.leetcode

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class `1_Two_SumTest` : StringSpec({
    "case-1" {
        val nums = intArrayOf(2, 7, 11, 15)
        val target = 9

        val actual = `1_Two_Sum`().twoSum(nums, target)

        actual shouldBe intArrayOf(0, 1)
    }

    "case-2" {
        val nums = intArrayOf(3, 2, 4)
        val target = 6

        val actual = `1_Two_Sum`().twoSum(nums, target)

        actual shouldBe intArrayOf(1, 2)
    }
})
