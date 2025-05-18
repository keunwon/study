package algorithm.programmers

class Lesson181836 {
    fun solution(picture: Array<String>, k: Int): Array<String> {
        val result = Array(picture.size * k) { "" }
        val sb = StringBuilder(picture[0].length * k)
        picture.forEachIndexed { index, p ->
            p.forEach { sb.append("$it".repeat(k)) }
            val key = sb.toString()
            repeat(k) { result[index * k + it] = key }
            sb.clear()
        }
        return result
    }
}
