package algorithm.leetcode

class `14_Longest_Common_Prefix` {
    fun longestCommonPrefix(strs: Array<String>): String {
        val min = strs.minOf { it.length }
        val result = StringBuilder(min)

        for (i in 0 until min) {
            val target = strs[0][i]
            val valid = strs.all { it[i] == target }
            if (valid) result.append(target) else break
        }
        return result.toString()
    }
}
