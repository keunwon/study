package algorithm.programmers

class Lesson120861 {
    fun solution(keyinput: Array<String>, board: IntArray): IntArray {
        val xRange = IntRange(-(board[0] / 2), board[0] / 2)
        val yRange = IntRange(-(board[1] / 2), board[1] / 2)
        var x = 0
        var y = 0

        keyinput.forEach { key ->
            var nx = x
            var ny = y

            when (key) {
                "up" -> ++ny
                "down" -> --ny
                "left" -> --nx
                "right" -> ++nx
            }

            if (nx in xRange && ny in yRange) {
                x = nx
                y = ny
            }
        }

        return intArrayOf(x, y)
    }
}
