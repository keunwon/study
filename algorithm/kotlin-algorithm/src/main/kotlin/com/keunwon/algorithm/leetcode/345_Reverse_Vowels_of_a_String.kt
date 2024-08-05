package com.keunwon.algorithm.leetcode

class `345_Reverse_Vowels_of_a_String` {
    fun reverseVowels(s: String): String {
        var left = 0
        var right = s.lastIndex
        val arr = s.toCharArray()
        val vowels = charArrayOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

        while (left <= right) {
            if (s[left] !in vowels) {
                ++left
            } else if (s[right] !in vowels) {
                --right
            } else {
                val tmp = arr[left]
                arr[left] = arr[right]
                arr[right] = tmp
                ++left
                --right
            }
        }
        return arr.joinToString("")
    }
}

fun main() {
    intArrayOf(1, 1).toMutableSet()

    `345_Reverse_Vowels_of_a_String`().reverseVowels("hello")
}
