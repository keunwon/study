package algorithm.leetcode

class `59_Spiral_Matrix_II` {
    fun generateMatrix(n: Int): Array<IntArray> {
        var (r1, c1) = 0 to 0
        var (r2, c2) = n - 1 to n - 1
        val result = Array(n) { IntArray(n) }
        var num = 1

        while (r1 <= r2 && c1 <= r2) {
            for (i in c1..c2) {
                result[r1][i] = num++
            }
            ++r1

            for (i in r1..r2) {
                result[i][c2] = num++
            }
            --c2

            if (r1 <= r2) {
                for (i in c2 downTo c1) {
                    result[r2][i] = num++
                }
                --r2
            }

            if (c1 < c2) {
                for (i in r2 downTo r1) {
                    result[i][c1] = num++
                }
                ++c1
            }
        }
        return result
    }
}

fun main() {
    `59_Spiral_Matrix_II`().generateMatrix(3)
        .also { arr -> println(arr.joinToString(",", "[", "]") { it.joinToString(",", "[", "]") }) } // expect: [[1,2,3],[8,9,4],[7,6,5]]
}
