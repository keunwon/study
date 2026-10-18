class Solution {
    fun longestCommonPrefix(strs: Array<String>): String {
        val target = strs[0]
        for (i in target.indices) {
            val valid = strs.all { str -> i < str.length && str[i] == target[i] }
            if (!valid) {
                return target.substring(0, i)
            }
        }
        return target
    }
}