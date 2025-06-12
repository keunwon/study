package algorithm.programmers

class Lesson181921 {
    fun solution(l: Int, r: Int): IntArray {
        return (l..r).filter { n -> "$n".all { it == '0' || it == '5' } }
            .ifEmpty { listOf(-1) }
            .toIntArray()
    }
}
