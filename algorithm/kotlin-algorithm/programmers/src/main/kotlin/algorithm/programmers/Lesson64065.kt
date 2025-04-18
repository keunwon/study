package algorithm.programmers

class Lesson64065 {
    fun solution(s: String): IntArray {
        val answer = mutableListOf<Int>()
        val arr = s.replace("{{", "")
            .replace("}}", "")
            .split("},{")
            .map { str -> str.split(",").map { it.toInt() } }
            .sortedBy { it.size }

        arr.forEach { numbers ->
            numbers.forEach { num -> if (!answer.contains(num)) answer.add(num) }
        }
        return answer.toIntArray()
    }
}
