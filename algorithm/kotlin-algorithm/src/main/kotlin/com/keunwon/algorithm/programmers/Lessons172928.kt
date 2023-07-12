package com.keunwon.algorithm.programmers

/**
 * Title: 공원 산책
 * Level: 1
 **/
class Lessons172928 {
    fun solution(park: Array<String>, routes: Array<String>): IntArray {
        var r = 0
        var c = 0

        for (i in park.indices) {
            for (j in park[0].indices) {
                if (park[i][j] == 'S') {
                    r = i
                    c = j
                    break
                }
            }
        }

        for (route in routes) {
            val (dir, distance) = route.split(" ").let { it[0] to it[1].toInt() }
            var (nr, nc) = r to c

            for (i in 1..distance) {
                when (dir) {
                    "N" -> nr--
                    "S" -> nr++
                    "W" -> nc--
                    "E" -> nc++
                }

                if (nr !in park.indices || nc !in park[0].indices) break
                if (park[nr][nc] == 'X') break

                if (i == distance) {
                    r = nr
                    c = nc
                }
            }
        }
        return intArrayOf(r, c)
    }
}
