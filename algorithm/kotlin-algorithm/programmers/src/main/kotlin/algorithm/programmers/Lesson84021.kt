package algorithm.programmers

import java.util.LinkedList

class Lesson84021 {
    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        val gameBlocks = extractBlocks(game_board, 0)
        val tableBlocks = extractBlocks(table, 1)
        val visited = BooleanArray(tableBlocks.size)
        var result = 0

        for (gameBlock in gameBlocks) {
            for ((i, tableBlock) in tableBlocks.withIndex()) {
                if (!visited[i] && matchBlock(gameBlock, tableBlock)) {
                    visited[i] = true
                    result += gameBlock.size
                    break
                }
            }
        }

        return result
    }

    private fun matchBlock(blockA: List<Node>, blockB: List<Node>): Boolean {
        if (blockA.size != blockB.size) return false

        var target = blockA.sorted()
        repeat(4) {
            if (target == blockB) return true

            target = target.map { Node(it.c, -it.r) }.sorted()
            val first = target[0]
            target = target.map { Node(it.r - first.r, it.c - first.c) }
        }

        return false
    }

    private fun extractBlocks(board: Array<IntArray>, target: Int): List<List<Node>> {
        val blocks = mutableListOf<List<Node>>()
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        val visited = Array(board.size) { BooleanArray(board[0].size) }

        for (i in board.indices) {
            for ((j, type) in board[i].withIndex()) {
                if (visited[i][j] || type != target) continue

                val q = LinkedList<Node>().apply { offer(Node(i, j)) }
                val block = mutableListOf<Node>()
                visited[i][j] = true

                while (q.isNotEmpty()) {
                    val cur = q.poll()
                    block.add(Node(cur.r - i, cur.c - j))

                    for ((mr, mc) in moves) {
                        val nr = cur.r + mr
                        val nc = cur.c + mc

                        if (nr in board.indices && nc in board[0].indices && !visited[nr][nc] && board[nr][nc] == target) {
                            visited[nr][nc] = true
                            q.offer(Node(nr, nc))
                        }
                    }
                }

                blocks.add(block.apply { sort() })
            }
        }

        return blocks
    }

    private data class Node(val r: Int, val c: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int =
            if (r != other.r) r.compareTo(other.r) else c.compareTo(other.c)
    }
}
