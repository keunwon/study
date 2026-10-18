class Solution {
    fun majorityElement(nums: IntArray): Int {
        var x = 0
        var count = 0

        for (n in nums) {
            if (count == 0) x = n
            if (x == n) ++count else --count
        }
        return x
    }
}