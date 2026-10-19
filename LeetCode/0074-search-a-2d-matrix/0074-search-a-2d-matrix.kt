class Solution {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var left = 0
        var right = matrix.size * matrix[0].size - 1

        while (left <= right) {
            val m = left + (right - left) / 2
            val r = m / matrix[0].size
            val c = m % matrix[0].size

            if (matrix[r][c] == target) {
                return true
            } else if (matrix[r][c] < target) {
                left = m + 1
            } else {
                right = m - 1
            }
        }
        return false
    }
}