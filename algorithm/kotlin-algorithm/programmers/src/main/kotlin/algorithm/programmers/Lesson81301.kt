package algorithm.programmers

class Lesson81301 {
    fun solution(s: String): Int {
        val map = mapOf(
            "zero" to "0",
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )
        var result = s

        map.forEach { (key, value) -> result = result.replace(key, value) }
        return result.toInt()
    }
}
