package com.keunwon.algorithm.leetcode

import java.util.*

class `127_Word_Ladder` {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val queue = LinkedList<Node>().apply { offer(Node(beginWord, 1)) }
        val wordSet = wordList.toMutableSet()

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.word == endWord) return cur.count

            for (i in cur.word.indices) {
                val arr = cur.word.toCharArray()

                for (c in 'a'..'z') {
                    arr[i] = c
                    val key = arr.concatToString()

                    if (wordSet.contains(key)) {
                        queue.offer(Node(key, cur.count + 1))
                        wordSet.remove(key)
                    }
                }
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
