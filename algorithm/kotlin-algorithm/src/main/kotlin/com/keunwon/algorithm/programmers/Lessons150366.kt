package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 표 병합
 * Level: 3
 **/
class Lessons150366 {
    fun solution(commands: Array<String>): Array<String> {
        val parent = IntArray(25001) { it }
        val words = Array(25001) { "" }
        val answer = mutableListOf<String>()

        for (command in commands) {
            val st = StringTokenizer(command)

            when (st.nextToken()) {
                "UPDATE" -> {
                    when (st.countTokens()) {
                        3 -> {
                            val (r, c) = Array(2) { st.nextToken().toInt() }
                            val findIndex = find(parent, getIndex(r, c))
                            words[findIndex] = st.nextToken()
                        }
                        2 -> {
                            val (old, new) = Array(2) { st.nextToken() }
                            words.forEachIndexed { i, word -> if (word == old) words[i] = new }
                        }
                    }
                }

                "MERGE" -> {
                    val (r1, c1, r2, c2) = IntArray(4) { st.nextToken().toInt() }
                    val findIndex1 = find(parent, getIndex(r1, c1))
                    val findIndex2 = find(parent, getIndex(r2, c2))

                    if (findIndex1 == findIndex2) continue

                    val word = words[findIndex1].ifBlank { words[findIndex2] }

                    words[findIndex1] = ""
                    words[findIndex2] = ""
                    union(parent, findIndex1, findIndex2)
                    words[find(parent, getIndex(r1, c1))] = word
                }

                "UNMERGE" -> {
                    val (r, c) = IntArray(2) { st.nextToken().toInt() }
                    val index = getIndex(r, c)
                    val findIndex = find(parent, index)
                    val word = words[findIndex]

                    words[findIndex] = ""
                    words[index] = word

                    words.indices
                        .filter { find(parent, it) == findIndex }
                        .forEach { parent[it] = it }
                }

                "PRINT" -> {
                    val (r, c) = IntArray(2) { st.nextToken().toInt() }
                    val findIndex = find(parent, getIndex(r, c))
                    answer.add(words[findIndex].ifBlank { "EMPTY" })
                }
            }
        }
        return answer.toTypedArray()
    }

    private fun find(parent: IntArray, n: Int): Int {
        return if (parent[n] == n) n else find(parent, parent[n])
    }

    private fun union(parent: IntArray, a: Int, b: Int) {
        val find1 = find(parent, a)
        val find2 = find(parent, b)
        return if (find1 < find2) parent[find2] = find1
        else parent[find1] = find2
    }

    private fun getIndex(r: Int, c: Int): Int = (r - 1) * 50 + c
}
