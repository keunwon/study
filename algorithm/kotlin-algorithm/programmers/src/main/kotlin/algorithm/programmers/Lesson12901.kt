package algorithm.programmers

class Lesson12901 {
    fun solution(a: Int, b: Int): String {
        val days = arrayOf("FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU")
        val months = intArrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        val sum = (0 until a - 1).sumOf { months[it] } + b - 1
        return days[sum % days.size]
    }
}

fun main() {
    Lesson12901().solution(5, 24).also { println(it) } // TUE
}
