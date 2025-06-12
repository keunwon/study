package algorithm.programmers

class Lesson42862 {
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        val clothes = IntArray(n + 1).apply {
            lost.forEach { --this[it] }
            reserve.forEach { ++this[it] }
        }

        for (i in 1..n) {
            if (clothes[i] >= 0) continue

            if (clothes[i - 1] > 0) {
                --clothes[i - 1]
                ++clothes[i]
            } else if (i < n && clothes[i + 1] > 0) {
                --clothes[i + 1]
                ++clothes[i]
            }
        }

        return clothes.count { it >= 0 } - 1
    }
}
