package algorithm.leetcode

class `867_Transpose_Matrix` {
    fun transpose(matrix: Array<IntArray>): Array<IntArray> {
        val result = Array(matrix[0].size) { IntArray(matrix.size) }
        for (i in matrix.indices) {
            for ((j, n) in matrix[i].withIndex()) {
                result[j][i] = n
            }
        }
        return result
    }
}
