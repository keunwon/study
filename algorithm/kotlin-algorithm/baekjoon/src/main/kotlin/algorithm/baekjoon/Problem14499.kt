package algorithm.baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 * <p>
 * 이름: 주사위 굴리기
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/14499">주사위 굴리기 (골드-4)</a>
 **/
class Problem14499 {
    fun solution(map: Array<IntArray>, x: Int, y: Int, dirs: IntArray): IntArray {
        val result = mutableListOf<Int>()
        val dices = IntArray(7)
        var r = x
        var c = y

        for (dir in dirs) {
            val (mr, mc) = moves.getValue(dir)
            val nr = r + mr
            val nc = c + mc

            if (nr in map.indices && nc in map[0].indices) {
                // 동=1, 서=2, 북=3, 남=4
                when (dir) {
                    1 -> {
                        val tmp = dices[3]
                        dices[3] = dices[2]
                        dices[2] = dices[6]
                        dices[6] = dices[4]
                        dices[4] = tmp
                    }

                    2 -> {
                        val tmp = dices[3]
                        dices[3] = dices[4]
                        dices[4] = dices[6]
                        dices[6] = dices[2]
                        dices[2] = tmp
                    }

                    3 -> {
                        val tmp = dices[3]
                        dices[3] = dices[5]
                        dices[5] = dices[6]
                        dices[6] = dices[1]
                        dices[1] = tmp
                    }

                    4 -> {
                        val tmp = dices[3]
                        dices[3] = dices[1]
                        dices[1] = dices[6]
                        dices[6] = dices[5]
                        dices[5] = tmp
                    }
                }


                if (map[nr][nc] == 0) {
                    map[nr][nc] = dices[6]
                } else {
                    dices[6] = map[nr][nc]
                    map[nr][nc] = 0
                }

                result.add(dices[3])
                r = nr
                c = nc
            }
        }

        return result.toIntArray()
    }

    companion object {
        private val moves = mapOf(
            1 to Pair(0, 1),
            2 to Pair(0, -1),
            3 to Pair(-1, 0),
            4 to Pair(1, 0),
        )
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m, x, y, k) = run {
        val st = StringTokenizer(readLine())
        IntArray(5) { st.nextToken().toInt() }
    }
    val map = Array(n) {
        val st = StringTokenizer(readLine())
        IntArray(m) { st.nextToken().toInt() }
    }
    val dirs = run {
        val st = StringTokenizer(readLine())
        IntArray(k) { st.nextToken().toInt() }
    }

    BufferedWriter(OutputStreamWriter(System.out)).let { bw ->
        Problem14499().solution(map, x, y, dirs).forEach {
            bw.write("$it")
            bw.newLine()
        }
        bw.flush()
    }
}
