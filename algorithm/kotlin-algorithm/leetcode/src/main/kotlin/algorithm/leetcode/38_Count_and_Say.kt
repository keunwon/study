package algorithm.leetcode

class `38_Count_and_Say` {
    fun countAndSay(n: Int): String {
        if (n == 1) return "1"
        return reformat(countAndSay(n - 1))
    }

    private fun reformat(s: String): String {
        val sb = StringBuilder()
        var c = s[0]
        var count = 1

        for (i in 1 until s.length) {
            if (c != s[i]) {
                sb.append(count).append(c)
                c = s[i]
                count = 1
            } else {
                ++count
            }
        }

        sb.append(count).append(c)
        return sb.toString()
    }
}

fun main() {
    `38_Count_and_Say`().countAndSay(4).also { println(it) } // 1211
    `38_Count_and_Say`().countAndSay(5).also { println(it) } // 111221
    `38_Count_and_Say`().countAndSay(6).also { println(it) } // 312211
}
