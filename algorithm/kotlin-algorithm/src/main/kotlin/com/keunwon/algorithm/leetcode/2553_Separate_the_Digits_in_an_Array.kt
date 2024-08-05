package com.keunwon.algorithm.leetcode

class `2553_Separate_the_Digits_in_an_Array` {
    fun separateDigits(nums: IntArray): IntArray {
        val str = nums.joinToString("")
        return IntArray(str.length) { str[it] - '0' }
    }
}
