package algorithm.leetcode

class `868_Binary_Gap` {
    fun binaryGap(n: Int): Int {
        val binary = n.toString(2)
        var s = binary.indexOf('1')
        var result = 0

        if (s == -1) return 0

        for (i in s + 1 until binary.length) {
            if (binary[i] == '1') {
                result = result.coerceAtLeast(i - s)
                s = i
            }
        }
        return result
    }
}
