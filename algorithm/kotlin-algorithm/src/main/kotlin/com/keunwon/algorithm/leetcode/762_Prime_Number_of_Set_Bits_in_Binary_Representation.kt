package com.keunwon.algorithm.leetcode

import kotlin.math.sqrt

class `762_Prime_Number_of_Set_Bits_in_Binary_Representation` {
    fun countPrimeSetBits(left: Int, right: Int): Int {
        return (left..right).count { it.countOneBits().isPrime() }
    }

    private fun Int.isPrime(): Boolean {
        if (this < 2) return false
        return (2..sqrt(this.toDouble()).toInt()).none { this % it == 0 }
    }
}

fun main() {
    `762_Prime_Number_of_Set_Bits_in_Binary_Representation`()
        .countPrimeSetBits(6, 10)
        .also { println(it) } // 4
}
