package algorithm.leetcode

class `2917_Find_the_K_or_of_an_Array` {
    fun findKOr(nums: IntArray, k: Int): Int {
        var result = 0

        for (i in 0 until 32) {
            val count = nums.count { n -> (n shr i) and 1 == 1 }
            if (k <= count) result += 1 shl i
        }
        return result
    }
}
