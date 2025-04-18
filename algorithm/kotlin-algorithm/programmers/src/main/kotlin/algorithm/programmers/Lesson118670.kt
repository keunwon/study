package algorithm.programmers

class Lesson118670 {
    fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
        val left = ArrayDeque<Int>()
        val mid = ArrayDeque<ArrayDeque<Int>>()
        val right = ArrayDeque<Int>()

        for (i in rc.indices) {
            mid.add(ArrayDeque())

            for ((j, n) in rc[i].withIndex()) {
                when (j) {
                    0 -> left.add(n)
                    rc[0].lastIndex -> right.add(n)
                    else -> mid[i].add(n)
                }
            }
        }

        for (op in operations) {
            when (op) {
                "ShiftRow" -> {
                    left.addFirst(left.removeLast())
                    right.addFirst(right.removeLast())
                    mid.addFirst(mid.removeLast())
                }

                "Rotate" -> {
                    mid.first().addFirst(left.removeFirst())
                    mid.last().addLast(right.removeLast())
                    right.addFirst(mid.first().removeLast())
                    left.addLast(mid.last().removeFirst())
                }
            }
        }

        val answer = Array(rc.size) { IntArray(rc[0].size) }
        for (i in rc.indices) {
            for (j in rc[0].indices) {
                val num = when (j) {
                    0 -> left.removeFirst()
                    rc[0].lastIndex -> right.removeFirst()
                    else -> mid[i].removeFirst()
                }
                answer[i][j] = num
            }
        }
        return answer
    }
}
