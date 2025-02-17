package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 마법사 상어와 토네이도
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/20057">마법사 상어와 토네이도 (골드-2)</a>
 **/
class Problem20057 {
    private lateinit var map: Array<IntArray>
    private val moves = arrayOf(0 to -1, 1 to 0, 0 to 1, -1 to 0)

    private val left = arrayOf(
        Triple(-2, 0, 2),
        Triple(-1, -1, 10),
        Triple(-1, 0, 7),
        Triple(-1, 1, 1),
        Triple(0, -2, 5),
        Triple(1, -1, 10),
        Triple(1, 0, 7),
        Triple(1, 1, 1),
        Triple(2, 0, 2),
    )
    private val right = Array(left.size) {
        val (r, c, d) = left[it]
        Triple(r, -c, d)
    }
    private val down = Array(left.size) {
        val (r, c, d) = left[it]
        Triple(-c, r, d)
    }
    private val up = Array(left.size) {
        val (r, c, d) = left[it]
        Triple(c, r, d)
    }

    init {
        val comparator = compareBy<Triple<Int, Int, Int>>({ it.first }, { it.second })
        left.sortWith(comparator)
        right.sortWith(comparator)
        down.sortWith(comparator)
        up.sortWith(comparator)
    }

    fun solution(map: Array<IntArray>): Int {
        this.map = map
        var (r, c) = Pair(map.size / 2, map.size / 2)
        var dir = 0
        var result = 0

        for (dist in 1..map.size) {
            repeat(2) {
                for (i in 0 until dist) {
                    r += moves[dir].first
                    c += moves[dir].second

                    if (r !in map.indices || c !in map[0].indices) break
                    result += spread(r, c, dir)
                }
                dir = (dir + 1) % moves.size
            }
        }

        return result
    }

    private fun spread(r: Int, c: Int, dir: Int): Int {
        val sand = map[r][c].also { map[r][c] = 0 }
        var ret = sand
        var outSand = 0
        val tornado = when (dir) {
            0 -> left
            1 -> down
            2 -> right
            3 -> up
            else -> error("not support dir: $dir")
        }

        for ((mr, mc, rate) in tornado) {
            val nr = r + mr
            val nc = c + mc
            val tmpSand = (sand * rate) / 100

            ret -= tmpSand
            if (nr in map.indices && nc in map[0].indices) {
                map[nr][nc] += tmpSand
            } else {
                outSand += tmpSand
            }
        }

        val nr = r + moves[dir].first
        val nc = c + moves[dir].second

        if (nr in map.indices && nc in map[0].indices) {
            map[nr][nc] += ret
        } else {
            outSand += ret
        }

        return outSand
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val map = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }

    Problem20057().solution(map).also(::println)
}
