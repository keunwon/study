package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 나무 재테크
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/16235">나무 재테크 (골드-3)</a>
 **/
class Problem16235 {
    private lateinit var board: Array<IntArray>
    private lateinit var lands: Array<IntArray>
    private lateinit var trees: MutableList<Tree>

    fun solution(k: Int, board: Array<IntArray>, treeInfos: Array<IntArray>): Int {
        this.board = board
        this.lands = Array(board.size) { IntArray(board[0].size) { 5 } }
        this.trees = treeInfos.map { Tree(it[0] - 1, it[1] - 1, it[2], true) }.toMutableList()

        repeat(k) {
            spring()
            summer()
            fall()
            winter()
        }
        return trees.size
    }

    private fun winter() {
        for (i in board.indices) {
            for ((j, n) in board[i].withIndex()) {
                lands[i][j] += n
            }
        }
    }

    private fun fall() {
        val moves = arrayOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)
        val newTrees = mutableListOf<Tree>()

        trees.forEach { tree ->
            if (!tree.alive || tree.age % 5 != 0) return@forEach

            moves.forEach { (mr, mc) ->
                val nr = tree.r + mr
                val nc = tree.c + mc

                if (nr in lands.indices && nc in lands[0].indices) {
                    newTrees.add(Tree(nr, nc, 1, true))
                }
            }
        }

        trees.addAll(newTrees)
    }

    private fun summer() {
        trees.forEach { tree -> if (!tree.alive) lands[tree.r][tree.c] += tree.age / 2 }
        trees.removeIf { !it.alive }
    }

    private fun spring() {
        trees.sortBy { it.age }
        trees.forEach { tree ->
            val diff = lands[tree.r][tree.c] - tree.age
            if (diff < 0) {
                tree.alive = false
            } else {
                ++tree.age
                lands[tree.r][tree.c] = diff
            }
        }
    }

    private class Tree(val r: Int, val c: Int, var age: Int, var alive: Boolean)
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (m, n, k) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(m) {
        val arr = br.readLine().split(" ")
        IntArray(m) { arr[it].toInt() }
    }
    val trees = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(3) { arr[it].toInt() }
    }

    Problem16235().solution(k, board, trees).also(::println)
}
