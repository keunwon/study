package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 청소년 상어
 * 난이도: 골드-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/19236">청소년 상어 (골드-2)</a>
 **/
class Problem19236 {
    private val moves = arrayOf(-1 to 0, -1 to -1, 0 to -1, 1 to -1, 1 to 0, 1 to 1, 0 to 1, -1 to 1)
    private var result = 0

    fun solution(infos: Array<IntArray>): Int {
        val map = Array(4) { Array<Fish?>(4) { null } }

        for (i in map.indices) {
            for (j in map[0].indices) {
                map[i][j] = Fish(infos[i][j * 2], infos[i][j * 2 + 1] - 1)
            }
        }

        val shark = map[0][0]!!
        map[0][0] = null
        dfs(0, 0, shark.dir, shark.id, map)
        return result
    }

    private fun dfs(r: Int, c: Int, dir: Int, sum: Int, map: Array<Array<Fish?>>) {
        result = result.coerceAtLeast(sum)
        moveFishes(r, c, map)

        for (i in 1..3) {
            val nr = (r + moves[dir].first * i)
            val nc = (c + moves[dir].second * i)

            if (nr in map.indices && nc in map[0].indices) {
                val fish = map[nr][nc] ?: continue
                val tmpMap = Array(map.size) { tr ->
                    Array(map[0].size) { tc -> map[tr][tc]?.let { Fish(it.id, it.dir) } }
                }

                tmpMap[nr][nc] = null
                dfs(nr, nc, fish.dir, sum + fish.id, tmpMap)
            }
        }
    }

    private fun moveFishes(r: Int, c: Int, map: Array<Array<Fish?>>) {
        for (id in 1..16) {
            loop@ for (i in map.indices) {
                for (j in map[i].indices) {
                    val fish = map[i][j]

                    if (fish?.id == id) {
                        for (k in moves.indices) {
                            val mIndex = (fish.dir + k) % moves.size
                            val nr = i + moves[mIndex].first
                            val nc = j + moves[mIndex].second

                            if (nr in map.indices && nc in map[0].indices && !(r == nr && c == nc)) {
                                fish.dir = mIndex
                                map[i][j] = map[nr][nc]
                                map[nr][nc] = fish
                                break@loop
                            }
                        }
                    }
                }
            }
        }
    }

    private class Fish(val id: Int, var dir: Int)
}

fun main() {
    val infos = Array(4) {
        val arr = readln().split(" ")
        IntArray(8) { arr[it].toInt() }
    }

    Problem19236().solution(infos).also(::println)
}
