package algorithm.programmers

class Lesson77485 {
    fun solution(t: String, p: String): Int {
        val words = t.windowed(p.length).map { it.toLong() }
        val pNum = p.toLong()
        return words.count { it <= pNum }
    }
}

fun main() {
    Lesson77485().solution("3141592", "271").also { println(it) }
}
