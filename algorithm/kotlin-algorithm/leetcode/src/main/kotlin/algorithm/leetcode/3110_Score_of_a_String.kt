package algorithm.leetcode

import kotlin.math.abs

class `3110_Score_of_a_String` {
    fun scoreOfString(s: String): Int {
        return (0 until s.lastIndex).sumOf { index -> abs(s[index] - s[index + 1]) }
    }
}

fun main() {
    `3110_Score_of_a_String`().scoreOfString("hello").also { println(it) }
    `3110_Score_of_a_String`().scoreOfString("zaz").also { println(it) }
}
