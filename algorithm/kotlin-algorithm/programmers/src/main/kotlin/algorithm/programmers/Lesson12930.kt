package algorithm.programmers

class Lesson12930 {
    fun solution(s: String): String = buildString {
        val letters = s.uppercase().toCharArray()
        var n = 0

        for (letter in letters) {
            n = if (letter == ' ') 0 else n + 1

            append(if (n % 2 == 1) letter else letter.lowercase())
        }
    }
}

fun main() {
    val list = intArrayOf(1, 2, 3)
    val l2 = listOf(1, 2, 3)

    Lesson12930().solution("try hello world").also { println(it) }
}
