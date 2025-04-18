package algorithm.leetcode

class `1822_Sign_of_the_Product_of_an_Array` {
    fun arraySign(nums: IntArray): Int {
        val n = nums.reduce { acc, n -> acc * n }
        return when {
            n < 0 -> -1
            n > 0 -> 1
            else -> 0
        }
    }
}

fun main() {
    `1822_Sign_of_the_Product_of_an_Array`().arraySign(intArrayOf(-1, -2, -3, -4, 3, 2, 1)).also { println(it) } // 1
    `1822_Sign_of_the_Product_of_an_Array`().arraySign(intArrayOf(1, 5, 0, 2, -3)).also { println(it) } // 0
    `1822_Sign_of_the_Product_of_an_Array`().arraySign(intArrayOf(-1, 1, -1, 1, -1)).also { println(it) } // -1
}
