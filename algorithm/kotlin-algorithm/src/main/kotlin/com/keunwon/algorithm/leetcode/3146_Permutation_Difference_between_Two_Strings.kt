package com.keunwon.algorithm.leetcode

import kotlin.math.abs

class `3146_Permutation_Difference_between_Two_Strings` {
    fun findPermutationDifference(s: String, t: String): Int {
        val map = t.indices.associateBy { t[it] }
        return s.withIndex().sumOf { abs(it.index - map.getValue(it.value)) }
    }
}

fun main() {
    `3146_Permutation_Difference_between_Two_Strings`()
        .findPermutationDifference("abc", "bac")
        .also { println(it) } // 2

    `3146_Permutation_Difference_between_Two_Strings`()
        .findPermutationDifference("abcde", "edbac")
        .also { println(it) } // 12
}
