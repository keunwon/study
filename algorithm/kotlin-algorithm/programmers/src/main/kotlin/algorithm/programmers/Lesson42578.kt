package algorithm.programmers

class Lesson42578 {
    fun solution(clothes: Array<Array<String>>): Int {
        val countMap = clothes.groupingBy { it[1] }.eachCount()
        return countMap.entries.fold(1) { acc, (_, n) -> acc * (n + 1) } - 1
    }
}
