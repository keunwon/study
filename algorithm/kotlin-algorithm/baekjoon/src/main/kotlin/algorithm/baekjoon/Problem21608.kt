package algorithm.baekjoon

/**
 * <p>
 * 이름: 상어 초등학교
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/21608">상어 초등학교 (골드-5)</a>
 **/
class Problem21608 {
    private lateinit var map: Array<IntArray>
    private lateinit var students: Map<Int, IntArray>
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(n: Int, students: Map<Int, IntArray>): Int {
        this.map = Array(n) { IntArray(n) }
        this.students = students

        students.forEach {
            val seat = assignSeat(it.key)
            map[seat.r][seat.c] = it.key
        }

        var result = 0
        for (i in 0 until n) {
            for (j in 0 until n) {
                val id = map[i][j]
                var favorite = 0

                for ((mr, mc) in moves) {
                    val nr = i + mr
                    val nc = j + mc

                    if (nr in map.indices &&
                        nc in map[0].indices &&
                        map[nr][nc] in students.getValue(id)
                    ) {
                        ++favorite
                    }
                }

                result += when (favorite) {
                    1 -> 1
                    2 -> 10
                    3 -> 100
                    4 -> 1000
                    else -> 0
                }
            }
        }
        return result
    }

    private fun assignSeat(id: Int): Seat {
        var seat: Seat? = null

        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] > 0) continue

                var favorite = 0
                var empty = 0

                for ((mr, mc) in moves) {
                    val nr = i + mr
                    val nc = j + mc

                    if (nr in map.indices && nc in map[0].indices) {
                        if (map[nr][nc] == 0) ++empty
                        else if (map[nr][nc] in students.getValue(id)) ++favorite
                    }
                }

                val tmpSeat = Seat(i, j, favorite, empty)
                if (seat == null || seat < tmpSeat) {
                    seat = tmpSeat
                }
            }
        }
        return seat!!
    }

    private class Seat(
        val r: Int,
        val c: Int,
        val favorite: Int,
        val empty: Int,
    ) : Comparable<Seat> {
        override fun compareTo(other: Seat): Int = when {
            favorite != other.favorite -> favorite.compareTo(other.favorite)
            empty != other.empty -> empty.compareTo(other.empty)
            r != other.r -> other.r.compareTo(r)
            else -> other.c.compareTo(c)
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val students = mutableMapOf<Int, IntArray>()

    repeat(n * n) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        students[arr[0]] = IntArray(4) { arr[it + 1] }
    }

    Problem21608().solution(n, students).also(::println)
}
