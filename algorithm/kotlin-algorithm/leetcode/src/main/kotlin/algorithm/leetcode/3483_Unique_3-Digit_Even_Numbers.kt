package algorithm.leetcode

class `3483_Unique_3-Digit_Even_Numbers` {
    private val result = mutableSetOf<Int>()

    fun totalNumbers(digits: IntArray): Int {
        val numbers = IntArray(10).apply { digits.forEach { ++this[it] } }
        dfs(0, 0, numbers)
        return result.size
    }

    private fun dfs(depth: Int, sum: Int, numbers: IntArray) {
        if (depth == 3) {
            if (sum >= 100 && sum % 2 == 0) result.add(sum)
            return
        }

        for (i in numbers.indices) {
            if (numbers[i] == 0) continue

            --numbers[i]
            dfs(depth + 1, sum * 10 + i, numbers)
            ++numbers[i]
        }
    }
}
