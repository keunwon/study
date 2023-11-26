package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons150366 {
    private val parent = IntArray(2501) { it }
    private val words = Array(2501) { "" }

    fun solution(commands: Array<String>): Array<String> {
        val answer = mutableListOf<String>()
        for (command in commands) {
            val st = StringTokenizer(command)
            when (st.nextToken()) {
                "UPDATE" -> {
                    if (st.countTokens() == 2) {
                        val (oldValue, newValue) = nextOptions(st)
                        words.forEachIndexed { index, word -> if (word == oldValue) words[index] = newValue }
                    } else if (st.countTokens() == 3) {
                        val (r, c, value) = nextOptions(st)
                        val findIndex = find(getIndex(r.toInt(), c.toInt()))
                        words[findIndex] = value
                    }
                }

                "MERGE" -> {
                    val (r1, c1, r2, c2) = nextOptions(st) { it.toInt() }
                    val findIndex1 = find(getIndex(r1, c1))
                    val findIndex2 = find(getIndex(r2, c2))

                    if (findIndex1 == findIndex2) continue

                    val word = words[findIndex1].ifBlank { words[findIndex2] }
                    words[findIndex1] = ""
                    words[findIndex2] = ""
                    union(findIndex1, findIndex2)
                    words[find(getIndex(r1, c1))] = word
                }

                "UNMERGE" -> {
                    val (r, c) = nextOptions(st) { it.toInt() }
                    val findIndex = find(getIndex(r, c))
                    val word = words[findIndex]

                    words[findIndex] = ""
                    words[getIndex(r, c)] = word
                    parent.indices.filter { find(it) == findIndex }.forEach { parent[it] = it }
                }

                "PRINT" -> {
                    val (r, c) = nextOptions(st) { it.toInt() }
                    val findIndex = find(getIndex(r, c))
                    answer.add(words[findIndex].ifBlank { "EMPTY" })
                }
            }
        }
        return answer.toTypedArray()
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        return if (find1 < find2) parent[find2] = find1 else parent[find1] = find2
    }

    private fun find(n: Int): Int = if (parent[n] == n) n else find(parent[n]).also { parent[n] = it }

    private fun getIndex(r: Int, c: Int): Int = (r - 1) * 50 + c

    private fun nextOptions(st: StringTokenizer): Array<String> = nextOptions(st) { it }

    private inline fun <reified T> nextOptions(st: StringTokenizer, f: (String) -> T): Array<T> =
        Array(st.countTokens()) { f(st.nextToken()) }
}

fun main() {
    ALessons150366().solution(
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
            "PRINT 1 4"
        )
    ).also { println(it.contentToString()) }

    ALessons150366().solution(
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
            "PRINT 1 1"
        )
    ).also { println(it.contentToString()) }
}
