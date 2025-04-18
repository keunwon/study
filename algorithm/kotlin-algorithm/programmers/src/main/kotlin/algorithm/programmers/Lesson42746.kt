package algorithm.programmers

class Lesson42746 {
    fun solution(numbers: IntArray): String {
        val answer = numbers.sortedWith { o1, o2 -> "$o2$o1".compareTo("$o1$o2") }.joinToString("")
        return if (answer[0] == '0') "0" else answer
    }
}
