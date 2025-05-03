package algorithm.leetcode

class `169_Majority_Element` {
    fun majorityElement(nums: IntArray): Int {
        var count = 0
        var target = 0

        nums.forEach { n ->
            if (count == 0) target = n
            if (n == target) ++count else --count
        }
        return target
    }
}
