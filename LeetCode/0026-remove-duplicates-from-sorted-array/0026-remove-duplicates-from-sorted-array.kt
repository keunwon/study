class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        val visited = BooleanArray(201)
        var idx = 0

        for (num in nums) {
            if (!visited[num + 100]) {
                visited[num + 100] = true
                nums[idx++] = num
            }
        }
        return idx
    }
}