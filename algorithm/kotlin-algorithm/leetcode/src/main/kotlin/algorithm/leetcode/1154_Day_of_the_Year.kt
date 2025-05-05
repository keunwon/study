package algorithm.leetcode

class `1154_Day_of_the_Year` {
    fun dayOfYear(date: String): Int {
        val (year, mm, dd) = date.split("-").map { it.toInt() }
        val months = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            months[2] = 29
        }
        return (1 until mm).sumOf { months[it] } + dd
    }
}
