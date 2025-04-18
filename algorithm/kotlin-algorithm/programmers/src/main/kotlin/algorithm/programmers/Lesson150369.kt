package algorithm.programmers

class Lesson150369 {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var d = 0
        var p = 0
        var answer = 0L

        for (i in n - 1 downTo 0) {
            d += deliveries[i]
            p += pickups[i]

            while (d > 0 || p > 0) {
                d -= cap
                p -= cap
                answer += (i + 1) * 2
            }
        }
        return answer
    }
}
