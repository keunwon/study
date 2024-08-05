package com.keunwon.algorithm.leetcode

class `2085_Count_Common_Words_With_One_Occurrence` {
    fun countWords(words1: Array<String>, words2: Array<String>): Int {
        val map1 = words1.groupingBy { it }.eachCount()
        val map2 = words2.groupingBy { it }.eachCount()
        return map1.count { it.value == 1 && map2.getOrDefault(it.key, 0) == 1 }
    }
}

fun main() {
    `2085_Count_Common_Words_With_One_Occurrence`().countWords(
        arrayOf("leetcode", "is", "amazing", "as", "is"),
        arrayOf("amazing", "leetcode", "is"),
    ).also { println(it) } // 2

    `2085_Count_Common_Words_With_One_Occurrence`().countWords(
        arrayOf("b", "bb", "bbb"),
        arrayOf("a", "aa", "aaa")
    ).also { println(it) } //  0

    `2085_Count_Common_Words_With_One_Occurrence`().countWords(
        arrayOf("a", "ab"),
        arrayOf("a", "a", "a", "ab")
    ).also { println(it) } // 1
}
