package algorithm.programmers

class Lesson120896 {
    fun solution(s: String): String {
        val alphabet = IntArray(26).apply { s.forEach { ++this[it - 'a'] } }
        return s.toList().filter { alphabet[it - 'a'] == 1 }.sorted().joinToString("")
    }
}
