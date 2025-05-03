package algorithm.leetcode

import java.util.LinkedList

class `127_Word_Ladder` {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val q = LinkedList<Node>().apply { offer(Node(beginWord, 1)) }
        val set = wordList.toMutableSet()

        while (q.isNotEmpty()) {
            val cur = q.poll()
            if (cur.word == endWord) return cur.count

            val arr = cur.word.toCharArray()
            for (i in arr.indices) {
                val tmp = arr[i]
                for (c in 'a'..'z') {
                    if (tmp == c) continue

                    arr[i] = c
                    val key = arr.concatToString()
                    if (set.contains(key)) {
                        q.offer(Node(key, cur.count + 1))
                        set.remove(key)
                    }
                }
                arr[i] = tmp
            }
        }
        return 0
    }

    private data class Node(val word: String, val count: Int)
}

fun main() {
    `127_Word_Ladder`()
        .ladderLength("hit", "cog", arrayListOf("hot", "dot", "dog", "lot", "log", "cog"))
        .also { println(it) } // 5

    `127_Word_Ladder`()
        .ladderLength("hit", "cog", arrayListOf("hot", "dot", "dog", "lot", "log"))
        .also { println(it) } // 0
}
