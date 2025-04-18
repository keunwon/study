package algorithm.baekjoon

/**
 * <p>
 * 이름: 주사위 윷놀이
 * 난이도: 골드-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17825">주사위 윷놀이 (골드-2)</a>
 **/
class Problem17825 {
    private lateinit var dices: IntArray
    private lateinit var root: Node

    private var result = 0

    fun solution(dices: IntArray): Int {
        this.dices = dices
        this.root = createNode()

        combination(0, IntArray(10))
        return result
    }

    private fun combination(depth: Int, players: IntArray) {
        if (depth == players.size) {
            var total = 0
            val nodes = Array(4) { root }

            for ((i, player) in players.withIndex()) {
                val dice = dices[i]
                var cur = nodes[player]

                for (j in 0 until dice) {
                    if (cur.isEnd) break
                    cur = if (j == 0 && cur.isBlue) cur.blueNode!! else cur.redNode!!
                }

                if (!cur.isEnd && nodes.any { it == cur }) {
                    total = 0
                    break
                } else {
                    nodes[player] = cur
                    total += cur.score
                }
            }

            result = result.coerceAtLeast(total)
            return
        }

        for (i in 0 until 4) {
            players[depth] = i
            combination(depth + 1, players)
        }
    }

    private fun createNode(): Node {
        val root = Node(0)
        val endNode = Node(40).apply { redNode = Node(0, isEnd = true) }
        val midNode = Node(25)
        var cur = root

        for (i in 2..38 step 2) {
            cur = cur.appendRed(Node(i))
        }
        cur.appendRed(endNode)

        cur = midNode
        cur = cur.appendRed(Node(30))
        cur = cur.appendRed(Node(35))
        cur.appendRed(endNode)

        cur = root.findNodeOrNull(10)!!
        cur.isBlue = true
        cur = cur.appendBlue(Node(13))
        cur = cur.appendRed(Node(16))
        cur = cur.appendRed(Node(19))
        cur.appendRed(midNode)

        cur = root.findNodeOrNull(20)!!
        cur.isBlue = true
        cur = cur.appendBlue(Node(22))
        cur = cur.appendRed(Node(24))
        cur.appendRed(midNode)

        cur = root.findNodeOrNull(30)!!
        cur.isBlue = true
        cur = cur.appendBlue(Node(28))
        cur = cur.appendRed(Node(27))
        cur = cur.appendRed(Node(26))
        cur.appendRed(midNode)

        return root
    }

    private class Node(
        val score: Int,
        var redNode: Node? = null,
        var isBlue: Boolean = false,
        var blueNode: Node? = null,
        var isEnd: Boolean = false,
    ) {
        fun appendRed(node: Node): Node {
            redNode = node
            return redNode!!
        }

        fun appendBlue(node: Node): Node {
            blueNode = node
            return blueNode!!
        }

        fun findNodeOrNull(score: Int): Node? {
            var cur: Node? = this
            while (cur != null) {
                if (cur.score == score) break
                cur = cur.redNode
            }
            return cur
        }
    }
}

fun main() {
    val numbers = readln().split(" ").map { it.toInt() }.toIntArray()
    Problem17825().solution(numbers).also(::println)
}
