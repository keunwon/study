package algorithm.programmers

import java.util.*

class Lesson172928 {
    private val moves = mapOf(
        'N' to intArrayOf(-1, 0),
        'S' to intArrayOf(1, 0),
        'W' to intArrayOf(0, -1),
        'E' to intArrayOf(0, 1)
    )

    fun solution(park: Array<String>, routes: Array<String>): IntArray {
        var (r, c) = 0 to 0

        for (i in park.indices) {
            for ((j, type) in park[i].withIndex()) {
                if (type == 'S') {
                    r = i
                    c = j
                    break
                }
            }
        }

        for (route in routes) {
            val st = StringTokenizer(route)
            val move = moves.getValue(st.nextToken()[0])
            val n = st.nextToken().toInt()

            var nr = r
            var nc = c
            var flag = true

            for (i in 0 until n) {
                nr += move[0]
                nc += move[1]

                if (nr !in park.indices || nc !in park[0].indices) {
                    flag = false
                    break
                }

                if (park[nr][nc] == 'X') {
                    flag = false
                    break
                }
            }

            if (flag) {
                r = nr
                c = nc
            }
        }
        return intArrayOf(r, c)
    }
}
