package algorithm.programmers

import kotlin.math.max

class Lesson258712 {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        val map = friends.indices.associateBy { friends[it] }
        val points = IntArray(friends.size)
        val history = Array(friends.size) { IntArray(friends.size) }

        for (gift in gifts) {
            val (a, b) = gift.split(" ").map { map.getValue(it) }
            ++points[a]
            --points[b]
            ++history[a][b]
        }

        var result = 0
        for (i in friends.indices) {
            var count = 0

            for (j in friends.indices) {
                if (i == j) continue

                if (history[i][j] > history[j][i]) ++count
                else if (history[i][j] == history[j][i] && points[i] > points[j]) ++count
            }
            result = max(result, count)
        }
        return result
    }
}
