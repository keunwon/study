package algorithm.leetcode

class `139_Word_Break` {
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val dp = BooleanArray(s.length + 1).apply { this[0] = true }

        for (i in 1..s.length) {
            for (word in wordDict) {
                val sIndex = i - word.length
                if (sIndex >= 0 && dp[sIndex] && s.substring(sIndex, i) == word) {
                    dp[i] = true
                    break
                }
            }
        }
        return dp.last()
    }
}

fun main() {
    `139_Word_Break`().wordBreak("leetcode", listOf("leet", "code")).also { println(it) } // true
    `139_Word_Break`().wordBreak("applepenapple", listOf("apple", "pen")).also { println(it) } // true
    `139_Word_Break`().wordBreak("catsandog", listOf("cats", "dog", "sand", "and", "cat")).also { println(it) } // false
}
