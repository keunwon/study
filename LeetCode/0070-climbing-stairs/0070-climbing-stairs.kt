class Solution {
    fun climbStairs(n: Int): Int {
        if (n == 1) return 1

        var one = 1
        var two = 2

        for (i in 3..n) {
            val next = one + two
            one = two
            two = next
        }
        return two
    }
}