package algorithm.leetcode

class `49_Group_Anagrams` {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return strs.groupBy { str -> str.toCharArray().apply { sort() }.concatToString() }.values.toList()
    }
}
