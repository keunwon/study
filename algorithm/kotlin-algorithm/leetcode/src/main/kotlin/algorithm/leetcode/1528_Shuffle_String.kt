package algorithm.leetcode

class `1528_Shuffle_String` {
    fun restoreString(s: String, indices: IntArray): String {
        val result = CharArray(s.length)

        for ((index, c) in s.withIndex()) {
            result[indices[index]] = c
        }
        return result.concatToString()
    }
}

fun main() {
    `1528_Shuffle_String`().restoreString(
        "codeleet",
        intArrayOf(4, 5, 6, 7, 0, 2, 1, 3)
    ).also { println(it) } // leetcode

    `1528_Shuffle_String`().restoreString(
        "abc",
        intArrayOf(0, 1, 2)
    ).also { println(it) } // abc
}
