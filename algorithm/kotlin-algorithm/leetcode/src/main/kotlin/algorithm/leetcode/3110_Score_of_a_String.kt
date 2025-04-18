package algorithm.leetcode

import kotlin.math.abs

class `3110_Score_of_a_String` {
    fun scoreOfString(s: String): Int {
        var result = 0
        for (i in 0 until s.lastIndex) {
            result += abs(s[i] - s[i + 1])
        }
        return result
    }
}

fun main() {
    `3110_Score_of_a_String`().scoreOfString("hello").also { println(it) }
    `3110_Score_of_a_String`().scoreOfString("zaz").also { println(it) }
}
