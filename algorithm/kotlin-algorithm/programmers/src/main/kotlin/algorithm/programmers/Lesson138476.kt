package algorithm.programmers

class Lesson138476 {
    fun solution(k: Int, tangerine: IntArray): Int {
        val quantities = tangerine.toList()
            .groupingBy { it }
            .eachCount().values
            .sortedDescending()
        var sum = 0

        for ((i, quantity) in quantities.withIndex()) {
            sum += quantity
            if (sum >= k) return i + 1
        }
        return tangerine.size
    }
}
