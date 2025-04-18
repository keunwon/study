package algorithm.programmers

class Lesson42748 {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        return commands.map { (i, j, k) ->
            array.slice(i - 1 until j).sorted()[k - 1]
        }.toIntArray()
    }
}

fun main() {
    Lesson42748().solution(
        intArrayOf(1, 5, 2, 6, 3, 7, 4),
        arrayOf(
            intArrayOf(2, 5, 3),
            intArrayOf(4, 4, 1),
            intArrayOf(1, 7, 3),
        )
    ).also { println(it.joinToString(", ")) } // 5, 6, 3
}
