package com.keunwon.algorithm.againresolve

class ALessons60062 {
    private lateinit var weak: IntArray
    private lateinit var dist: IntArray
    private lateinit var spreadWeak: IntArray

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        this.weak = weak
        this.dist = dist
        this.spreadWeak = weak + weak.map { it + n }

        for (size in 1..dist.size) {
            val valid = permutation(0, IntArray(size), BooleanArray(size))
            if (valid) return size
        }
        return -1
    }

    private fun permutation(depth: Int, picks: IntArray, visited: BooleanArray): Boolean {
        if (depth == picks.size) return check(picks)

        for ((i, d) in dist.withIndex()) {
            if (visited[i]) continue

            visited[i] = true
            picks[depth] = d
            if (permutation(depth + 1, picks, visited)) return true
            visited[i] = false
        }
        return false
    }

    private fun check(picks: IntArray): Boolean {
        for (i in weak.indices) {
            var startIndex = i
            var pickIndex = 0
            var flag = true

            while (pickIndex < picks.size) {
                for (j in i until weak.size) {
                    val diff = spreadWeak[j] - spreadWeak[startIndex]
                    if (diff > picks[pickIndex]) {
                        startIndex = j
                        ++pickIndex

                        if (pickIndex == picks.size) {
                            flag = false
                            break
                        }
                    }
                }
                ++pickIndex
            }
            if (flag) return true
        }
        return true
    }
}

fun main() {
    ALessons60062().solution(
        12,
        intArrayOf(1, 5, 6, 10),
        intArrayOf(1, 2, 3, 4),
    ).also(::println)

    ALessons60062().solution(
        12,
        intArrayOf(1, 3, 4, 9, 10),
        intArrayOf(3, 5, 7)
    ).also(::println)
}
