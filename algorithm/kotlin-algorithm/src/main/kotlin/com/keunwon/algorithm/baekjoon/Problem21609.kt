package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: 상어 중학교
 * 난이도: 골드-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/21609">상어 중학교 (골드-2)</a>
 **/
class Problem21609 {
    private lateinit var map: Array<IntArray>

    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(m: Int, map: Array<IntArray>): Int {
        this.map = map
        var result = 0

        while (true) {
            val region = findMaxRegion().onEach { map[it.first][it.second] = -2 }

            if (region.isEmpty()) break

            result += region.size * region.size
            downMap()
            rotate()
            downMap()
        }
        return result
    }

    private fun rotate() {
        val tmpMap = Array(map.size) { IntArray(map[0].size) }

        for (i in map.indices) {
            for (j in map[0].indices) {
                tmpMap[i][j] = map[j][map[0].lastIndex - i]
            }
        }

        for (i in map.indices) {
            System.arraycopy(tmpMap[i], 0, map[i], 0, map[0].size)
        }
    }

    private fun downMap() {
        for (i in map[0].indices) {
            var j = map.size
            while (j-- > 1) {
                if (map[j][i] != -2) continue

                for (k in j - 1 downTo 0) {
                    if (map[k][i] == -1) {
                        j = k
                        break
                    } else if (map[k][i] != -2) {
                        map[j][i] = map[k][i]
                        map[k][i] = -2
                        break
                    }
                }
            }
        }
    }

    private fun findMaxRegion(): List<Pair<Int, Int>> {
        val visited = Array(map.size) { BooleanArray(map[0].size) }
        val regions = mutableListOf<List<Pair<Int, Int>>>()

        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    val region = connectRegion(Pair(i, j), visited)
                    if (region.size > 1) {
                        regions.add(region)
                        region.forEach { (r, c) -> if (map[r][c] == 0) visited[r][c] = false }
                    }
                }
            }
        }

        regions.sortWith(
            compareBy(
                { -it.size },
                { region -> -region.count { map[it.first][it.second] == 0 } },
                { -it[0].first },
                { -it[0].second },
            )
        )

        return regions.getOrElse(0) { emptyList() }
    }

    private fun connectRegion(start: Pair<Int, Int>, visited: Array<BooleanArray>): List<Pair<Int, Int>> {
        val q = LinkedList<Pair<Int, Int>>().apply { offer(start) }
        val region = mutableListOf<Pair<Int, Int>>()

        visited[start.first][start.second] = true

        while (q.isNotEmpty()) {
            val (r, c) = q.poll().also(region::add)

            for ((mr, mc) in moves) {
                val nr = r + mr
                val nc = c + mc

                if (nr in map.indices &&
                    nc in map[0].indices &&
                    !visited[nr][nc] &&
                    (map[nr][nc] == map[start.first][start.second] || map[nr][nc] == 0)
                ) {
                    visited[nr][nc] = true
                    q.offer(nr to nc)
                }
            }
        }
        return region
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }

    Problem21609().solution(m, map).also(::println)
}
