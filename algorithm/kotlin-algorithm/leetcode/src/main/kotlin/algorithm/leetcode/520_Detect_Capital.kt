package algorithm.leetcode

class `520_Detect_Capital` {
    fun detectCapitalUse(word: String): Boolean {
        return word.all { it.isUpperCase() } ||
                word.all { it.isLowerCase() } ||
                (word[0].isUpperCase() && word.substring(1).all { it.isLowerCase() })
    }
}
