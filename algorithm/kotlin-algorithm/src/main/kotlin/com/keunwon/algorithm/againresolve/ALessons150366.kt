package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons150366 {
    private val parents = IntArray(2501) { it }
    private val words = Array(2501) { "" }

    fun solution(commands: Array<String>): Array<String> {
        val answer = mutableListOf<String>()

        for (command in commands) {
            val st = StringTokenizer(command)

            when (st.nextToken()) {
                "UPDATE" -> {
                    if (st.countTokens() == 3) {
                        val (r, c) = IntArray(2) { st.nextToken().toInt() }
                        val findIndex = find(getIndex(r, c))
                        words[findIndex] = st.nextToken()
                    } else if (st.countTokens() == 2) {
                        val (old, new) = Array(2) { st.nextToken() }
                        words.forEachIndexed { i, word -> if (old == word) words[i] = new }
                    }
                }
                "MERGE" -> {
                    val (r1, c1, r2, c2) = Array(4) { st.nextToken().toInt() }
                    val findIndex1 = find(getIndex(r1, c1))
                    val findIndex2 = find(getIndex(r2, c2))

                    if (findIndex1 == findIndex2) continue

                    val tmp = words[findIndex1].ifBlank { words[findIndex2] }
                    words[findIndex1] = ""
                    words[findIndex2] = ""
                    union(findIndex1, findIndex2)
                    words[find(getIndex(r1, c1))] = tmp
                }
                "UNMERGE" -> {
                    val (r, c) = IntArray(2) { st.nextToken().toInt() }
                    val index = getIndex(r, c)
                    val findIndex = find(index)
                    val tmp = words[findIndex]

                    words[findIndex] = ""
                    words[index] = tmp

                    parents.indices
                        .filter { find(it) == findIndex }
                        .forEach { parents[it] = it }
                }
                "PRINT" -> {
                    val (r, c) = IntArray(2) { st.nextToken().toInt() }
                    val findIndex = find(getIndex(r, c))
                    answer.add(words[findIndex].ifBlank { "EMPTY" })
                }
            }
        }
        return answer.toTypedArray()
    }

    private fun getIndex(r: Int, c: Int): Int = (r - 1) * 50 + c

    private fun find(n: Int): Int {
        return if (parents[n] == n) n else find(parents[n])
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        return if (find1 < find2) parents[find2] = find1
        else parents[find1] = find2
    }
}

fun main() {
    arrayOf(
        arrayOf(
            "UPDATE 1 1 menu",
            "UPDATE 1 2 category",
            "UPDATE 2 1 bibimbap",
            "UPDATE 2 2 korean",
            "UPDATE 2 3 rice",
            "UPDATE 3 1 ramyeon",
            "UPDATE 3 2 korean",
            "UPDATE 3 3 noodle",
            "UPDATE 3 4 instant",
            "UPDATE 4 1 pasta",
            "UPDATE 4 2 italian",
            "UPDATE 4 3 noodle",
            "MERGE 1 2 1 3",
            "MERGE 1 3 1 4",
            "UPDATE korean hansik",
            "UPDATE 1 3 group",
            "UNMERGE 1 4",
            "PRINT 1 3",
            "PRINT 1 4",
        ),
        arrayOf(
            "UPDATE 1 1 a",
            "UPDATE 1 2 b",
            "UPDATE 2 1 c",
            "UPDATE 2 2 d",
            "MERGE 1 1 1 2",
            "MERGE 2 2 2 1",
            "MERGE 2 1 1 1",
            "PRINT 1 1",
            "UNMERGE 2 2",
            "PRINT 1 1",
        )
    ).forEach { commands ->
        ALessons150366().solution(commands).also { println(it.joinToString(", ")) }
    }
}
