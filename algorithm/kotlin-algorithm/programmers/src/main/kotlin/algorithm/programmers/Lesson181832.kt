package algorithm.programmers

class Lesson181832 {
    fun solution(n: Int): Array<IntArray> {
        val result = Array(n) { IntArray(n) }
        var target = 1
        var (r1, r2) = 0 to n - 1
        var (c1, c2) = 0 to n - 1

        while (target <= n * n) {
            for (i in c1..c2) {
                result[r1][i] = target++
            }
            ++r1

            for (i in r1..r2) {
                result[i][c2] = target++
            }
            --c2

            for (i in c2 downTo c1) {
                result[r2][i] = target++
            }
            --r2

            for (i in r2 downTo r1) {
                result[i][c1] = target++
            }
            ++c1
        }

        return result
    }
}
