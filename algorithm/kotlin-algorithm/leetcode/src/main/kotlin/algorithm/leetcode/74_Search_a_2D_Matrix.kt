package algorithm.leetcode

class `74_Search_a_2D_Matrix` {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        var i = 0
        var j = matrix[0].lastIndex

        while (i in matrix.indices && j in matrix[0].indices) {
            if (matrix[i][j] == target) return true
            if (matrix[i][j] < target) ++i else --j
        }
        return false
    }
}
