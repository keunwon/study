package com.keunwon.algorithm.leetcode

class `151_Reverse_Words_in_a_String` {
    fun reverseWords(s: String): String {
        val arr = s.trim().split(" ").filter { it.isNotBlank() }.toTypedArray()
        var left = 0
        var right = arr.lastIndex

        while (left < right) {
            val tmp = arr[left]
            arr[left] = arr[right]
            arr[right] = tmp
            ++left
            --right
        }
        return arr.joinToString(" ")
    }
}

fun main() {
    `151_Reverse_Words_in_a_String`().reverseWords("the sky is blue").also { println(it) }
    `151_Reverse_Words_in_a_String`().reverseWords("  hello world  ").also { println(it) }
    `151_Reverse_Words_in_a_String`().reverseWords("a good   example").also { println(it) }
}
