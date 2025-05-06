package algorithm.leetcode

class `1399_Count_Largest_Group` {
    fun countLargestGroup(n: Int): Int {
        val arr = IntArray(37)
        var max = 0

        for (i in 1..n) {
            var target = i
            var idx = 0

            while (target > 0) {
                val mod = target % 10
                idx += mod
                target /= 10
            }
            ++arr[idx]
            max = max.coerceAtLeast(arr[idx])
        }
        return arr.withIndex().count { it.value == max }
    }
}
