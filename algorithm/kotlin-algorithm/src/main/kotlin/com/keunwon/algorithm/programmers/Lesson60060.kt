package com.keunwon.algorithm.programmers

class Lesson60060 {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        val forward = Trie()
        val backward = Trie()

        words.forEach { word ->
            forward.insert(word)
            backward.insert(word.reversed())
        }
        return queries.map { query ->
            if (query[0] == '?') backward.getMathCount(query.reversed())
            else forward.getMathCount(query)
        }.toIntArray()
    }

    private class Trie(
        val map: MutableMap<Int, Int> = mutableMapOf(),
        val child: Array<Trie?> = Array(26) { null },
    ) {
        fun insert(word: String) {
            var node = this
            node.map[word.length] = node.map.getOrDefault(word.length, 0) + 1

            for (c in word) {
                if (node.child[c - 'a'] == null) node.child[c - 'a'] = Trie()
                node = node.child[c - 'a']!!
                node.map[word.length] = node.map.getOrDefault(word.length, 0) + 1
            }
        }

        fun getMathCount(word: String): Int {
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
