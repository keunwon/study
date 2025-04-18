package algorithm.programmers

import java.util.*
import kotlin.math.max
import kotlin.math.min

class Lesson81303 {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        val undo = Stack<Int>()
        var tableSize = n
        var cur = k

        for (str in cmd) {
            val st = StringTokenizer(str)

            when (st.nextToken()) {
                "U" -> {
                    val x = st.nextToken().toInt()
                    cur = max(0, cur - x)
                }

                "D" -> {
                    val x = st.nextToken().toInt()
                    cur = min(tableSize - 1, cur + x)
                }

                "C" -> {
                    undo.push(cur)
                    --tableSize
                    if (tableSize == cur) --cur
                }

                "Z" -> {
                    val num = undo.pop()
                    if (num <= cur) ++cur
                    ++tableSize
                }
            }
        }
        return buildString {
            append("O".repeat(tableSize))
            while (undo.isNotEmpty()) {
                insert(undo.pop(), "X")
            }
        }
    }
}
