class Solution {
    fun rotate(matrix: Array<IntArray>): Unit {
        for (i in matrix.indices) {
            for (j in i until matrix[i].size) {
                val tmp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = tmp
            }
        }

        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size / 2) {
                val k = matrix[i].lastIndex - j
                val x = matrix[i][j]
                matrix[i][j] = matrix[i][k]
                matrix[i][k] = x
            }
        }
    }
}