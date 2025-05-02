package algorithm.leetcode

import kotlin.math.absoluteValue
import kotlin.math.sign

class `7_Reverse_Integer` {
    fun reverse(x: Int): Int {
        var target = x.absoluteValue
        var result = 0

        while (target > 0) {
            if (result > Int.MAX_VALUE / 10) {
                return 0
            }

            result = result * 10 + target % 10
            target /= 10
        }
        return result * x.sign
    }
}
