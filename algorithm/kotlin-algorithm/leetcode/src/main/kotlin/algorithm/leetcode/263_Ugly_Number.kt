package algorithm.leetcode

class `263_Ugly_Number` {
    fun isUgly(n: Int): Boolean {
        val factors  = intArrayOf(2, 3, 5)
        var target = n

        for (f in factors) {
            while (target > 0 && target % f == 0) {
                target /= f
            }
        }
        return target == 1
    }
}
