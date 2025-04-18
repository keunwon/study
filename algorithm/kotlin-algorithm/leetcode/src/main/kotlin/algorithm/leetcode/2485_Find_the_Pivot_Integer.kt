package algorithm.leetcode

class `2485_Find_the_Pivot_Integer` {
    fun pivotInteger(n: Int): Int {
        if (n == 1) return n

        var l = 1
        var r = n
        var sum1 = l
        var sum2 = r

        while (l < r) {
            if (sum1 < sum2) {
                sum1 += ++l
            } else {
                sum2 += --r
            }

            if (sum1 == sum2 && l == r) {
                return l
            }
        }

        return -1
    }
}

fun main() {
    `2485_Find_the_Pivot_Integer`().pivotInteger(8).also { println(it) }
    `2485_Find_the_Pivot_Integer`().pivotInteger(1).also { println(it) }
}
