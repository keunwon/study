package algorithm.programmers

class Lesson152995 {
    fun solution(scores: Array<IntArray>): Int {
        val whanho = scores[0]
        var rank = 1
        var max = 0

        scores.sortWith(compareBy({ -it[0] }, { it[1] }))

        for ((a, b) in scores) {
            if (b < max) {
                if (whanho[0] == a && whanho[1] == b) return -1
            } else {
                max = b
                if (whanho[0] + whanho[1] < a + b) ++rank
            }
        }
        return rank
    }
}
