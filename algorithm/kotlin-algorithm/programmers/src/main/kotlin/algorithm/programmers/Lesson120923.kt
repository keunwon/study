package algorithm.programmers

class Lesson120923 {
    fun solution(num: Int, total: Int): IntArray {
        val start = total / num - (num - 1) / 2
        return IntArray(num) { start + it }
    }
}
