package algorithm.programmers

class Lesson60062 {
    private lateinit var weak: IntArray
    private lateinit var dist: IntArray
    private lateinit var spreadWeak: IntArray

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        this.weak = weak
        this.dist = dist
        this.spreadWeak = weak + weak.map { it + n }

        for (size in 1..dist.size) {
            val valid = permutation(0, IntArray(size), BooleanArray(dist.size))
            if (valid) return size
        }
        return -1
    }

    private fun permutation(depth: Int, picks: IntArray, visited: BooleanArray): Boolean {
        if (depth == picks.size) return check(picks)

        for ((i, d) in dist.withIndex()) {
            if (!visited[i]) {
                visited[i] = true
                picks[depth] = d
                if (permutation(depth + 1, picks, visited)) return true
                visited[i] = false
            }
        }
        return false
    }

    private fun check(picks: IntArray): Boolean {
        for (i in weak.indices) {
            var sIndex = i
            var pIndex = 0
            var flag = true

            for (j in i until i + weak.size) {
                val diff = spreadWeak[j] - spreadWeak[sIndex]
                if (diff > picks[pIndex]) {
                    sIndex = j
                    ++pIndex
                }
                if (pIndex == picks.size) {
                    flag = false
                    break
                }
            }
            if (flag) return true
        }
        return false
    }
}
