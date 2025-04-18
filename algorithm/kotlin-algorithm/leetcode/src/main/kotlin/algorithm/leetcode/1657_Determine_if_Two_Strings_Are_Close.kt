package algorithm.leetcode

class `1657_Determine_if_Two_Strings_Are_Close` {
    fun closeStrings(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) return false

        val map1 = word1.groupingBy { it }.eachCount()
        val map2 = word2.groupingBy { it }.eachCount()

        if (map1.size != map2.size || map1.keys != map2.keys) return false
        return map1.values.sorted() == map2.values.sorted()
    }
}

fun main() {
    `1657_Determine_if_Two_Strings_Are_Close`().closeStrings("abc", "bca").also { println(it) } // true
    `1657_Determine_if_Two_Strings_Are_Close`().closeStrings("a", "aa").also { println(it) } // false
    `1657_Determine_if_Two_Strings_Are_Close`().closeStrings("cabbba", "abbccc").also { println(it) } // true
}
