class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return strs.groupBy { str ->
            val count = IntArray(26)
            str.forEach { ++count[it - 'a'] }
            count.contentHashCode()
        }.values.toList()
    }
}