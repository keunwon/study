package algorithm.leetcode

class `18_4Sum` {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val t = target.toLong()

        nums.sort()

        for (i in nums.indices) {
            if (i > 0 && nums[i] == nums[i - 1]) continue

            for (j in i + 1 until nums.size) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue

                var left = j + 1
                var right = nums.lastIndex

                while (left < right) {
                    val sum = nums[i].toLong() + nums[j] + nums[left] + nums[right]

                    if (sum == t) {
                        result.add(listOf(nums[i], nums[j], nums[left], nums[right]))

                        while (left < right && nums[left] == nums[left + 1]) ++left
                        while (left < right && nums[right] == nums[right - 1]) --right
                        ++left
                        --right
                    } else if (sum < t) {
                        ++left
                    } else {
                        --right
                    }
                }
            }
        }
        return result
    }
}

fun main() {
    `18_4Sum`().fourSum(intArrayOf(1000000000,1000000000,1000000000,1000000000), -294967296)
        .also { println(it) } // expect: []
}
