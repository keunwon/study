package algorithm.leetcode

class `414_Third_Maximum_Number` {
    fun thirdMax(nums: IntArray): Int {
        var a: Int? = null
        var b: Int? = null
        var c: Int? = null

        for (num in nums) {
            if (a == num || b == num || c == num) continue

            if (a == null || a < num) {
                c = b
                b = a
                a = num
            } else if (b == null || b < num) {
                c = b
                b = num
            } else if (c == null || c < num) {
                c = num
            }
        }
        return c ?: a!!
    }
}

fun main() {
    `414_Third_Maximum_Number`().thirdMax(intArrayOf(1, -2147483648, 2)).also { println(it) }
}
