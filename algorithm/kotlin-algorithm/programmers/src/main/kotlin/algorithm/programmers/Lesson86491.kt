package algorithm.programmers

class Lesson86491 {
    fun solution(sizes: Array<IntArray>): Int {
        sizes.forEach { it.sort() }
        return sizes.maxOfOrNull { it[0] }!! * sizes.maxOfOrNull { it[1] }!!
    }
}
