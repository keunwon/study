package algorithm.leetcode

class `179_Largest_Number` {
    fun largestNumber(nums: IntArray): String {
        val result = nums.sortedWith { o1, o2 -> "$o2$o1".compareTo("$o1$o2") }.joinToString("")
        return if (result[0] == '0') "0" else result
    }
}
