package algorithm.leetcode

class `54_Spiral_Matrix` {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        if (matrix.isEmpty()) return listOf()

        val result = mutableListOf<Int>()
        var (r1, c1) = 0 to 0
        var (r2, c2) = matrix.lastIndex to matrix[0].lastIndex

        while (r1 <= r2 && c1 <= c2) {
            for (i in c1..c2) {
                result.add(matrix[r1][i])
            }
            ++r1

            for (i in r1..r2) {
                result.add(matrix[i][c2])
            }
            --c2

            if (r1 <= r2) {
                for (i in c2 downTo c1) {
                    result.add(matrix[r2][i])
                }
                --r2
            }

            if (c1 <= c2) {
                for (i in r2 downTo r1) {
                    result.add(matrix[i][c1])
                }
                ++c1
            }
        }
        return result
    }
}

fun main() {
    `54_Spiral_Matrix`().spiralOrder(
        arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )
    ).also { println(it.joinToString(", ")) } // 1,2,3,6,9,8,7,4,5

    `54_Spiral_Matrix`().spiralOrder(
        arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(5, 6, 7, 8),
            intArrayOf(9, 10, 11, 12)
        )
    ).also { println(it.joinToString(", ")) } // 1,2,3,4,8,12,11,10,9,5,6,7
}
