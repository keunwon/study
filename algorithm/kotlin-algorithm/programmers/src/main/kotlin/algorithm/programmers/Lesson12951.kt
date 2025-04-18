package algorithm.programmers

class Lesson12951 {
    fun solution(s: String): String {
        return s.lowercase()
            .split(" ")
            .joinToString(" ") {
                if (it.isBlank()) ""
                else it[0].uppercase() + it.substring(1)
            }
    }
}
