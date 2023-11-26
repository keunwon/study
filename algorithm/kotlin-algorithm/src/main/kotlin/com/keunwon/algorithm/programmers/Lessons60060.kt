package com.keunwon.algorithm.programmers

/**
 * Title: 가사 검색
 * Level: 4
 **/
class Lessons60060 {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        val forward = Trie()
        val backward = Trie()

        words.forEach {
            forward.insert(it)
            backward.insert(it.reversed())
        }
        return queries.map {
            if (it[0] == '?') backward.find(it.reversed()) else forward.find(it)
        }.toIntArray()
    }

    private class Trie(
        val map: MutableMap<Int, Int> = mutableMapOf(),
        val child: Array<Trie?> = Array(26) { null }
    ) {
        fun insert(word: String) {
            var node = this
            map[word.length] = map.getOrDefault(word.length, 0) + 1

            for (c in word) {
                if (node.child[c - 'a'] == null) node.child[c - 'a'] = Trie()
                node = node.child[c - 'a']!!
                node.map[word.length] = node.map.getOrDefault(word.length, 0) + 1
            }
        }

        fun find(word: String): Int {
            var node: Trie? = this
            for (c in word) {
                if (node == null) return 0
                if (c == '?') return node.map.getOrDefault(word.length, 0)
                node = node.child[c - 'a']
            }
            return 0
        }
    }
}
