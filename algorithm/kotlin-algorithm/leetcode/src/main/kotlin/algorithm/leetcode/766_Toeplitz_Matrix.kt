package algorithm.leetcode

class `766_Toeplitz_Matrix` {
    fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
        for (i in 0 until matrix.lastIndex) {
            for (j in 0 until matrix[i].lastIndex) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) return false
            }
        }
        return true
    }
}
