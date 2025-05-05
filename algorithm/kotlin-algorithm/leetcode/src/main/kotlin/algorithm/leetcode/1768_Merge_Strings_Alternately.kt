package algorithm.leetcode

class `1768_Merge_Strings_Alternately` {
    fun mergeAlternately(word1: String, word2: String): String {
        val sb = StringBuilder(word1.length + word2.length)
        var idx1 = 0
        var idx2 = 0

        while (idx1 < word1.length && idx2 < word2.length) {
            sb.append(word1[idx1++])
            sb.append(word2[idx2++])
        }

        if (idx1 < word1.length) sb.append(word1.substring(idx1))
        if (idx2 < word2.length) sb.append(word2.substring(idx2))
        return sb.toString()
    }
}
