package algorithm.leetcode

class `27_Remove_Element` {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var index = 0

        for (i in nums.indices) {
            if (nums[i] != `val`) {
                nums[index] = nums[i]
                ++index
            }
        }
        return index
    }
}

fun main() {
    `27_Remove_Element`().removeElement(
        intArrayOf(3, 2, 2, 3),
        3
    ).also { println(it) } // 2

    `27_Remove_Element`().removeElement(
        intArrayOf(0, 1, 2, 2, 3, 0, 4, 2),
        2,
    ).also { println(it) }
}
