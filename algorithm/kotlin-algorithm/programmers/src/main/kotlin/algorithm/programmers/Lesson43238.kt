package algorithm.programmers

class Lesson43238 {
    fun solution(n: Int, times: IntArray): Long {
        times.sort()

        var left = 0L
        var right = times.last().toLong() * n

        while (left <= right) {
            val mid = (left + right) / 2
            val sum = times.sumOf { t -> mid / t }

            if (n <= sum) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}
