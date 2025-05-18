package algorithm.programmers

class Lesson181919 {
    fun solution(n: Int): IntArray {
        return generateSequence(n) { if (it % 2 == 0) it / 2 else it * 3 + 1 }
            .takeWhile { it > 1 }
            .plus(1)
            .toList()
            .toIntArray()
    }
}
