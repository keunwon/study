package algorithm.programmers

class Lesson258709_2 {
    private lateinit var dice: Array<IntArray>

    private var max = -1
    private var res = intArrayOf()

    fun solution(dice: Array<IntArray>): IntArray {
        this.dice = dice
        dfs(BooleanArray(dice.size), 0, 0)
        return res
    }

    private fun dfs(visited: BooleanArray, startIndex: Int, depth: Int) {
        if (depth == visited.size / 2) {
            val selectA = visited.indices.filter { visited[it] }
            val selectB = visited.indices.filter { !visited[it] }
            val sumsA = generateSumsTo(IntArray(501), selectA, 0, 0)
            val sumsB = generateSumsTo(IntArray(501), selectB, 0, 0)

            var winCount = 0
            var bWin = 0

            for (i in 1..500) {
                bWin += sumsB[i - 1]
                if (sumsA[i] > 0) winCount += bWin * sumsA[i]
            }

            if (winCount > max) {
                max = winCount
                res = IntArray(selectA.size) { selectA[it] + 1 }
            }
            return
        }

        for (i in startIndex until visited.size) {
            visited[i] = true
            dfs(visited, i + 1, depth + 1)
            visited[i] = false
        }
    }

    private fun generateSumsTo(sums: IntArray, select: List<Int>, sum: Int, depth: Int): IntArray {
        if (depth == select.size) {
            ++sums[sum]
            return sums
        }

        val diceIndex = select[depth]
        for (d in dice[diceIndex]) {
            generateSumsTo(sums, select, sum + d, depth + 1)
        }
        return sums
    }
}
