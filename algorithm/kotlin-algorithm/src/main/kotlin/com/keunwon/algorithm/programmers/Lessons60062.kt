package com.keunwon.algorithm.programmers

/**
 * Title: 외벽 점검
 * Level: 3
 **/
class Lessons60062 {
    private lateinit var weak: IntArray
    private lateinit var dist: IntArray
    private lateinit var spreadWeak: IntArray

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        this.weak = weak
        this.dist = dist.apply { sortDescending() }
        this.spreadWeak = weak + weak.map { it + n }

        for (i in 1..dist.size) {
            val valid = permutation(0, IntArray(i), BooleanArray(dist.size))
            if (valid) return i
        }
        return -1
    }

    private fun permutation(depth: Int, distances: IntArray, visited: BooleanArray): Boolean {
        if (depth == distances.size) return check(distances)

        for (i in dist.indices) {
            if (visited[i]) continue

            visited[i] = true
            distances[depth] = dist[i]
            if (permutation(depth + 1, distances, visited)) return true
            visited[i] = false
        }
        return false
    }

    private fun check(distances: IntArray): Boolean {
        for (i in weak.indices) {
            var startIndex = i
            var distIndex = 0
            var flag = true

            while (distIndex < distances.size) {
                for (j in i until weak.size + i) {
                    val diff = spreadWeak[j] - spreadWeak[startIndex]
                    if (diff > distances[distIndex]) {
                        startIndex = j
                        distIndex++

                        if (distIndex == distances.size) {
                            flag = false
                            break
                        }
                    }
                }
                distIndex++
            }
            if (flag) return true
        }
        return false
    }
}

