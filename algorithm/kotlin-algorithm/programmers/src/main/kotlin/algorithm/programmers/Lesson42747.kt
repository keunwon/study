package algorithm.programmers

class Lesson42747 {
    fun solution(citations: IntArray): Int {
        citations.sort()

        for (i in citations.indices) {
            val hIndex = citations.size - i
            if (citations[i] >= hIndex) return hIndex
        }

        return 0
    }
}
