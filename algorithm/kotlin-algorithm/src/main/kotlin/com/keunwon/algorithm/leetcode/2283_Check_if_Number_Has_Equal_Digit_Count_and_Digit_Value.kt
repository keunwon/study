package com.keunwon.algorithm.leetcode

class `2283_Check_if_Number_Has_Equal_Digit_Count_and_Digit_Value` {
    fun digitCount(num: String): Boolean {
        val map = num.groupingBy { it.digitToInt() }.eachCount()
        return num.indices.all { num[it] - '0' == map.getOrDefault(it, 0) }
    }
}

fun main() {
    `2283_Check_if_Number_Has_Equal_Digit_Count_and_Digit_Value`().digitCount("1210").also { println(it) } // true
    `2283_Check_if_Number_Has_Equal_Digit_Count_and_Digit_Value`().digitCount("030").also { println(it) } // false
}
