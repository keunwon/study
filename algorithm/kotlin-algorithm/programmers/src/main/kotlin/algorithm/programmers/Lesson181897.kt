package algorithm.programmers

class Lesson181897 {
    fun solution(n: Int, slicer: IntArray, num_list: IntArray): IntArray {
        val (a, b, c) = slicer
        return when (n) {
            1 -> num_list.sliceArray(0..b)
            2 -> num_list.sliceArray(a until num_list.size)
            3 -> num_list.sliceArray(a..b)
            4 -> num_list.slice(a..b step c).toIntArray()
            else -> intArrayOf()
        }
    }
}
