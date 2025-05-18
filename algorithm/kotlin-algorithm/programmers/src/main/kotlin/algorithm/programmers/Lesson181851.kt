package algorithm.programmers

class Lesson181851 {
    fun solution(rank: IntArray, attendance: BooleanArray): Int {
        val (a, b, c) = rank.indices.filter { attendance[it] }.sortedBy { rank[it] }
        return a * 10000 + 100 * b + c
    }
}
