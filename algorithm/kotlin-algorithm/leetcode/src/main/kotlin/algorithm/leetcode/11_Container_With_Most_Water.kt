package algorithm.leetcode

class `11_Container_With_Most_Water` {
    fun maxArea(height: IntArray): Int {
        var l = 0
        var r = height.lastIndex
        var result = 0

        while (l < r) {
            val min = height[l].coerceAtMost(height[r])
            val area = min * (r - l)
            result = result.coerceAtLeast(area)

            if (height[l] < height[r]) ++l else --r
        }
        return result
    }
}
