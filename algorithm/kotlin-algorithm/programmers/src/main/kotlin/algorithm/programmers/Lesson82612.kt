package algorithm.programmers

class Lesson82612 {
    fun solution(price: Int, money: Int, count: Int): Long {
        val sum = (1L..count).sumOf { price * it }
        return if (sum > money) sum - money else 0
    }
}
