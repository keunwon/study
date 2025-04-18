package algorithm.programmers

import java.util.*

class Lesson84021 {
    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        val gameBlocks = extraBlocks(game_board, 0)
        val tableBlocks = extraBlocks(table, 1)
        return matchCount(gameBlocks, tableBlocks)
    }

    private fun matchCount(gameBlocks: List<List<Node>>, tableBlocks: List<List<Node>>): Int {
        val visited = BooleanArray(tableBlocks.size)
        var count = 0

        for (gameBlock in gameBlocks) {
            for ((i, tableBlock) in tableBlocks.withIndex()) {
                if (!visited[i] && match(gameBlock, tableBlock)) {
                    visited[i] = true
                    count += gameBlock.size
                    break
                }
            }
        }
        return count
    }

    private fun match(gameBlock: List<Node>, tableBlock: List<Node>): Boolean {
        if (gameBlock.size != tableBlock.size) return false

        var target = tableBlock.sorted()
        repeat(4) {
            if (target == gameBlock) return true

            target = target.map { Node(it.c, -it.r) }.sorted()
            val start = target[0]
            target = target.map { Node(it.r - start.r, it.c - start.c) }
        }
        return false
    }

    private fun extraBlocks(board: Array<IntArray>, target: Int): List<List<Node>> {
        val blocks = mutableListOf<List<Node>>()
        val visited = Array(board.size) { BooleanArray(board[0].size) }

        for (i in board.indices) {
            for ((j, num) in board[i].withIndex()) {
                if (!visited[i][j] && num == target) {
                    val block = bfs(Node(i, j), visited, board, target)
                    blocks.add(block)
                }
            }
        }
        return blocks
    }

    private fun bfs(start: Node, visited: Array<BooleanArray>, board: Array<IntArray>, target: Int): List<Node> {
        val block = mutableListOf<Node>()
        val queue = LinkedList<Node>().apply { offer(start) }
        visited[start.r][start.c] = true

        while (queue.isNotEmpty()) {
            val cur = queue.poll()
            block.add(Node(cur.r - start.r, cur.c - start.c))

            for ((mr, mc) in moves) {
                val nr = cur.r + mr
                val nc = cur.c + mc

                if (nr !in visited.indices || nc !in visited[0].indices) continue
                if (visited[nr][nc] || board[nr][nc] != target) continue

                queue.offer(Node(nr, nc))
                visited[nr][nc] = true
            }
        }
        return block.sorted()
    }

    private data class Node(val r: Int, val c: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int =
            if (r == other.r) c.compareTo(other.c) else r.compareTo(other.r)
    }

    companion object {
        private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    }
}
