package algorithm.leetcode

class `438_Find_All_Anagrams_in_a_String` {
    fun findAnagrams(s: String, p: String): List<Int> {
        if (p.length > s.length) return listOf()

        val sArr = IntArray(26)
        val pArr = IntArray(26)

        for (i in p.indices) {
            ++sArr[s[i] - 'a']
            ++pArr[p[i] - 'a']
        }

        val result = mutableListOf<Int>()

        if (sArr.contentEquals(pArr)) result.add(0)

        for (i in p.length until s.length) {
            ++sArr[s[i] - 'a']
            --sArr[s[i - p.length] - 'a']
            if (sArr.contentEquals(pArr)) result.add(i - p.length + 1)
        }
        return result
    }
}
