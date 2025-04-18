package algorithm.programmers

import kotlin.math.max

class Lesson87390 {
    fun solution(n: Int, left: Long, right: Long): LongArray {
        return (left..right).map { max(it / n, it % n) + 1 }.toLongArray()
    }
}
