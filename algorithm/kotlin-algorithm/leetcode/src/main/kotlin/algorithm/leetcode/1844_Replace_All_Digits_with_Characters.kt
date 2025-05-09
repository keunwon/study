package algorithm.leetcode

class `1844_Replace_All_Digits_with_Characters` {
    fun replaceDigits(s: String): String {
        val arr = s.toCharArray()
        for (i in 1 until s.length step 2) {
            val c = s[i - 1]
            val n = s[i] - '0'
            arr[i] = (c + n)
        }
        return arr.concatToString()
    }
}
