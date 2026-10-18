class Solution {
    fun maxArea(height: IntArray): Int {
        var l = 0
        var r = height.size - 1
        var res = 0

        while (l < r) {
            var min = minOf(height[l], height[r])
            res = maxOf(res, min * (r - l))

            if (height[l] > height[r]) {
                --r
            } else {
                ++l
            }
        }
        return res
    }
}