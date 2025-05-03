package algorithm.leetcode

class `191_Number_of_1_Bits` {
    fun hammingWeight(n: Int): Int {
        var target = n
        var result = 0

        while (target > 0) {
            if (target and 1 == 1) ++result
            target = target shr 1
        }
        return result
    }
}

fun main() {
    `191_Number_of_1_Bits`().hammingWeight(11).also { println(it) }
}
