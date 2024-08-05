package com.keunwon.algorithm.leetcode

class `2160_Minimum_Sum_of_Four_Digit_Number_After_Splitting_Digits` {
    fun minimumSum(num: Int): Int {
        val numbers = "$num".toCharArray().sorted()
        return "${numbers[0]}${numbers[2]}".toInt() + "${numbers[1]}${numbers[3]}".toInt();
    }
}

fun main() {
    `2160_Minimum_Sum_of_Four_Digit_Number_After_Splitting_Digits`().minimumSum(2932).also { println(it) } // 52
    `2160_Minimum_Sum_of_Four_Digit_Number_After_Splitting_Digits`().minimumSum(4009).also { println(it) } // 13
}
