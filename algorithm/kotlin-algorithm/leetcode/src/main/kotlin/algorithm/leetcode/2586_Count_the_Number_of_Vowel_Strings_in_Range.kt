package algorithm.leetcode

class `2586_Count_the_Number_of_Vowel_Strings_in_Range` {
    fun vowelStrings(words: Array<String>, left: Int, right: Int): Int {
        val vowels = charArrayOf('a', 'e', 'i', 'o', 'u')
        return (left..right).count { index ->
            val word = words[index]
            vowels.contains(word.first()) && vowels.contains(word.last())
        }
    }
}
