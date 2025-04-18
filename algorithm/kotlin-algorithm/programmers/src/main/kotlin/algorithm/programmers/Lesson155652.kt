package algorithm.programmers

class Lesson155652 {
    fun solution(s: String, skip: String, index: Int): String {
        val alphabets = ('a'..'z').filter { it !in skip }
        return s.toCharArray().joinToString("") {
            val findIndex = alphabets.indexOf(it)
            "${alphabets[(findIndex + index) % alphabets.size]}"
        }
    }
}
