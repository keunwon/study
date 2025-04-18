package algorithm.leetcode

class `212_Word_Search_II` {
    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        val trie = Trie().apply { words.forEach { insert(it) } }
        val result = mutableSetOf<String>()
        val visited = Array(board.size) { BooleanArray(board[0].size) }

        for (i in board.indices) {
            for (j in board[0].indices) {
                find(trie, Pair(i, j), StringBuilder(), result, board, visited)
            }
        }
        return result.toList()
    }

    private fun find(
        trie: Trie,
        position: Pair<Int, Int>,
        word: StringBuilder,
        result: MutableSet<String>,
        board: Array<CharArray>,
        visited: Array<BooleanArray>,
    ) {
        if (position.first !in visited.indices ||
            position.second !in visited[0].indices ||
            visited[position.first][position.second]
        ) {
            return
        }

        val c = board[position.first][position.second]
        val nextTrie = trie.child[c - 'a'] ?: return

        word.append(c)
        if (nextTrie.isEnd) result.add(word.toString())

        visited[position.first][position.second] = true

        find(nextTrie, Pair(position.first - 1, position.second), word, result, board, visited)
        find(nextTrie, Pair(position.first, position.second + 1), word, result, board, visited)
        find(nextTrie, Pair(position.first + 1, position.second), word, result, board, visited)
        find(nextTrie, Pair(position.first, position.second - 1), word, result, board, visited)

        visited[position.first][position.second] = false
        word.deleteCharAt(word.lastIndex)
    }

    private class Trie(
        val child: Array<Trie?> = arrayOfNulls(26),
        var isEnd: Boolean = false,
    ) {
        fun insert(word: String) {
            var cur = this
            for (c in word) {
                if (cur.child[c - 'a'] == null) cur.child[c - 'a'] = Trie()
                cur = cur.child[c - 'a']!!
            }
            cur.isEnd = true
        }
    }
}

fun main() {
    `212_Word_Search_II`().findWords(
        arrayOf(
            charArrayOf('o', 'a', 'a', 'n'),
            charArrayOf('e', 't', 'a', 'e'),
            charArrayOf('i', 'h', 'k', 'r'),
            charArrayOf('i', 'f', 'l', 'v')
        ),
        arrayOf("oath", "pea", "eat", "rain")
    ).also { println(it.joinToString(", ")) } // "eat","oath"
}
