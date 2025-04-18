package algorithm.leetcode

class `91_Decode_Ways` {
    fun numDecodings(s: String): Int {
        val dp = IntArray(s.length + 1)

        if (s[0] == '0') return 0
        dp[1] = 1

        // 101
        // 1127110
        for (i in 2 until dp.size) {
            if (s[i - 1] != '0') {
                dp[i] += dp[i - 1]
            }
        }
        return dp.last()
    }
}

fun main() {
    `91_Decode_Ways`().numDecodings("12").also { println(it) } // 2
    `91_Decode_Ways`().numDecodings("226").also { println(it) } // 3
    `91_Decode_Ways`().numDecodings("06").also { println(it) } // 0
    `91_Decode_Ways`().numDecodings("10").also { println(it) } // 1
    `91_Decode_Ways`().numDecodings("100").also { println(it) } // 0
}
