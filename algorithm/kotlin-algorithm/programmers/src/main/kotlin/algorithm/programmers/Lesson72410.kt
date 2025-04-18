package algorithm.programmers

class Lesson72410 {
    fun solution(new_id: String): String {
        return new_id.lowercase()
            .replace("""[^a-z0-9-_.]""".toRegex(), "")
            .replace("""\.{2,}""".toRegex(), ".")
            .replace("""^\.""", "")
            .replace("""\.$""", "")
            .let { it.ifBlank { "a" } }
            .take(15)
            .replace("""\.$""", "")
            .let { if (it.length >= 2) it + "${it.last()}".repeat(3 - it.length) else it }
    }
}
