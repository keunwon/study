package algorithm.leetcode

class `13_Roman_to_Integer` {
    fun romanToInt(s: String): Int {
        val map = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000,
        )
        var result = 0

        for (i in 0 until s.lastIndex) {
            val n1 = map.getValue(s[i])
            val n2 = map.getValue(s[i + 1])

            if (n1 >= n2) {
                result += n1
            } else {
                result -= n1
            }
        }
        return result + map.getValue(s.last())
    }
}
