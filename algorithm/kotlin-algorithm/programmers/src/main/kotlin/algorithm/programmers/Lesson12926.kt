package algorithm.programmers

class Lesson12926 {
    fun solution(s: String, n: Int): String = buildString {
        s.forEach {
            val next = it + n
            val letter = when {
                it in 'A'..'Z' && next > 'Z' -> next - 26
                it in 'a'..'z' && next > 'z' -> next - 26
                it.isLetter() -> next
                else -> ' '
            }
            append(letter)
        }
    }
}
