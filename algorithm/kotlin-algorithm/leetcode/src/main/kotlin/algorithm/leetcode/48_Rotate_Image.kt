package algorithm.leetcode

class `48_Rotate_Image` {
    fun rotate(matrix: Array<IntArray>): Unit {
        val tmpArr = Array(matrix.size) { IntArray(matrix[0].size) }
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                tmpArr[i][j] = matrix[matrix.lastIndex - j][i]
            }
        }

        for (i in tmpArr.indices) {
            System.arraycopy(tmpArr[i], 0, matrix[i], 0, matrix[i].size)
        }
    }
}
