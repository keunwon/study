package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 2048 (Easy)
 * 난이도: 골드-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/12100">2048 (Easy) (골드-1)</a>
 **/
class Problem12100 {
    private lateinit var board: Array<IntArray>
    private var result = 0

    fun solution(board: Array<IntArray>): Int {
        this.board = board

        combination(0, IntArray(5))
        return result
    }

    private fun combination(depth: Int, picks: IntArray) {
        if (depth == picks.size) {
            val map = Array(board.size) { board[it].copyOf() }

            for (dir in picks) {
                val visited = Array(map.size) { BooleanArray(map[0].size) }

                when (dir) {
                    0 -> {
                        for (r in map.indices) {
                            for (c in map[r].indices) {
                                move(map, visited, Pair(r, c), dir)
                            }
                        }
                    }

                    1 -> {
                        for (c in map.lastIndex downTo 0) {
                            for (r in map.indices) {
                                move(map, visited, Pair(r, c), dir)
                            }
                        }
                    }

                    2 -> {
                        for (r in map.lastIndex downTo 0) {
                            for (c in map[r].indices) {
                                move(map, visited, Pair(r, c), dir)
                            }
                        }
                    }

                    3 -> {
                        for (c in map[0].indices) {
                            for (r in map.indices) {
                                move(map, visited, Pair(r, c), dir)
                            }
                        }
                    }
                }
            }

            val max = map.maxOf { arr -> arr.max() }
            result = result.coerceAtLeast(max)
            return
        }

        for (dir in moves.indices) {
            picks[depth] = dir
            combination(depth + 1, picks)
        }
    }

    private fun move(
        map: Array<IntArray>,
        visited: Array<BooleanArray>,
        p: Pair<Int, Int>,
        dir: Int,
    ) {
        var (r, c) = p
        val (mr, mc) = moves[dir]

        if (map[r][c] == 0) return

        while (true) {
            val nr = r + mr
            val nc = c + mc

            if (nr !in map.indices || nc !in map[0].indices || visited[nr][nc]) break

            if (map[r][c] == map[nr][nc]) {
                visited[nr][nc] = true
                map[nr][nc] *= 2
                map[r][c] = 0
                break
            } else if (map[nr][nc] == 0) {
                map[nr][nc] = map[r][c]
                map[r][c] = 0
                r = nr
                c = nc
            } else if (map[nr][nc] != 0) {
                break
            }
        }
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}

fun main() {
    val n = readln().toInt()
    val board = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    println(Problem12100().solution(board))
}
