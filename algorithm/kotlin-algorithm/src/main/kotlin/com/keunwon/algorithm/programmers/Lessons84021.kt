package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 퍼즐 조각 채우기
 * Level: 3
 **/
class Lessons84021 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        val gameBlocks = extraBlocks(game_board, 0)
        val tableBlocks = extraBlocks(table, 1)
        return countByMatchCount(gameBlocks, tableBlocks)
    }

    private fun countByMatchCount(gameBlocks: List<List<Position>>, tableBlocks: List<List<Position>>): Int {
        val visited = BooleanArray(gameBlocks.size)
        var count = 0

        for (tableBlock in tableBlocks) {
            for ((i, gameBlock) in gameBlocks.withIndex()) {
                if (tableBlock.size != gameBlock.size) continue
                if (visited[i] || !matchBlock(tableBlock, gameBlock)) continue

                visited[i] = true
                count += gameBlock.size
                break
            }
        }
        return count
    }

    private fun matchBlock(tableBlock: List<Position>, gameBlock: List<Position>): Boolean {
        var target = tableBlock.sorted()

        repeat(4) {
            val start = target.first()
            target = target.map { Position(it.r - start.r, it.c - start.c) }

            if (gameBlock == target) return true
            target = target.map { Position(it.c, -it.r) }.sorted()
        }
        return false
    }

    private fun extraBlocks(map: Array<IntArray>, target: Int): List<List<Position>> {
        val visited = Array(map.size) { BooleanArray(map[0].size) }
        val blocks = mutableListOf<List<Position>>()

        for (i in map.indices) {
            for (j in map[0].indices) {
                if (!visited[i][j] && map[i][j] == target) {
                    val block = findBlock(Position(i, j), map, visited, target)
                    blocks.add(block)
                }
            }
        }
        return blocks
    }

    private fun findBlock(
        src: Position,
        map: Array<IntArray>,
        visited: Array<BooleanArray>,
        target: Int,
    ): List<Position> {
        visited[src.r][src.c] = true

        val queue = LinkedList<Position>().apply { offer(src) }
        val block = mutableListOf(Position(0, 0))

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in map.indices || nc !in map[0].indices) continue
                if (visited[nr][nc] || map[nr][nc] != target) continue

                visited[nr][nc] = true
                queue.offer(Position(nr, nc))
                block.add(Position(nr - src.r, nc - src.c))
            }
        }
        return block.sorted()
    }

    private data class Position(val r: Int, val c: Int) : Comparable<Position> {
        override fun compareTo(other: Position): Int {
            return if (r == other.r) c.compareTo(other.c) else r.compareTo(other.r)
        }
    }
}
