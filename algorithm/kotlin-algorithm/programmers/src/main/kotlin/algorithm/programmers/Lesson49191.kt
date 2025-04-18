package algorithm.programmers

class Lesson49191 {
    fun solution(n: Int, results: Array<IntArray>): Int {
        val map = Array(n + 1) { IntArray(n + 1) }.apply {
            results.forEach { (a, b) ->
                this[a][b] = 1
                this[b][a] = -1
            }
        }

        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    if (map[j][i] == 1 && map[i][k] == 1) {
                        map[j][k] = 1
                        map[k][j] = -1
                    } else if (map[j][i] == -1 && map[i][k] == -1) {
                        map[j][k] = -1
                        map[k][j] = 1
                    }
                }
            }
        }
        return map.count { arr -> arr.count { it == 0 } == 2 }
    }
}
