package algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: 스타트 택시
 * 난이도: 골드-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/19238">스타트 택시 (골드-2)</a>
 **/
class Problem19238 {
    private lateinit var map: Array<IntArray>
    private lateinit var passengers: Array<Array<Pair<Int, Int>?>>

    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(fuel: Int, map: Array<IntArray>, driver: Pair<Int, Int>, routes: Array<IntArray>): Int {
        this.map = map
        this.passengers = Array(map.size) { Array<Pair<Int, Int>?>(map[0].size) { null } }.apply {
            routes.forEach { (r1, c1, r2, c2) -> this[r1 - 1][c1 - 1] = Pair(r2 - 1, c2 - 1) }
        }

        var ret = fuel
        var start = Pair(driver.first - 1, driver.second - 1)

        for (i in routes.indices) {
            val passenger = findMinDistanceNodeOrNull(start) ?: return -1

            ret -= passenger.d
            if (ret < 0) return -1

            val destination = findMinDistanceNodeOrNull(
                Pair(passenger.r, passenger.c),
                passengers[passenger.r][passenger.c]
            ) ?: return -1

            ret -= destination.d
            if (ret < 0) return -1
            ret += destination.d * 2

            passengers[passenger.r][passenger.c] = null
            start = destination.r to destination.c
        }

        return ret
    }

    private fun findMinDistanceNodeOrNull(
        start: Pair<Int, Int>,
        end: Pair<Int, Int>? = null,
    ): Node? {
        val q = PriorityQueue<Node>().apply { offer(Node(start.first, start.second, 0)) }
        val visited = Array(map.size) { BooleanArray(map[0].size) }.apply { this[start.first][start.second] = true }

        while (q.isNotEmpty()) {
            val cur = q.poll()

            if ((end != null && end.first == cur.r && end.second == cur.c) ||
                (end == null && passengers[cur.r][cur.c] != null)
            ) {
                return cur
            }

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr in map.indices && nc in map[0].indices && !visited[nr][nc] && map[nr][nc] != 1) {
                    visited[nr][nc] = true
                    q.offer(Node(nr, nc, cur.d + 1))
                }
            }
        }

        return null
    }

    private class Node(val r: Int, val c: Int, val d: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int = when {
            d != other.d -> d.compareTo(other.d)
            r != other.r -> r.compareTo(other.r)
            else -> c.compareTo(other.c)
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m, fuel) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }
    val driver = br.readLine().split(" ").let { Pair(it[0].toInt(), it[1].toInt()) }
    val routes = Array(m) {
        val arr = br.readLine().split(" ")
        IntArray(4) { arr[it].toInt() }
    }

    Problem19238().solution(fuel, map, driver, routes).also(::println)
}
