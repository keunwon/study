package algorithm.leetcode

class `73_Set_Matrix_Zeroes` {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val nodes = mutableListOf<Pair<Int, Int>>()
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

        for (i in matrix.indices) {
            for ((j, n) in matrix[i].withIndex()) {
                if (n == 0) nodes.add(i to j)
            }
        }

        for ((r, c) in nodes) {
            for ((mr, mc) in moves) {
                var nr = r + mr
                var nc = c + mc

                while (nr in matrix.indices && nc in matrix[0].indices) {
                    matrix[nr][nc] = 0
                    nr += mr
                    nc += mc
                }
            }
        }
    }
}
