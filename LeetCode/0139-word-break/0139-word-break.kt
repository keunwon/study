class Solution {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val dp = BooleanArray(s.length + 1)
        dp[0] = true
        
        for (i in 0..s.length) {
            for (word in wordDict) {
                val sIdx = i - word.length
                if (sIdx >= 0 && dp[sIdx] && word == s.substring(sIdx, i)) {
                    dp[i] = true
                    break
                }
            }
        }
        return dp[s.length]
    }
}