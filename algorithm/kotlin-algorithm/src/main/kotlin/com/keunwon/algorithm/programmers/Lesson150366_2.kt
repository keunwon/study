package com.keunwon.algorithm.programmers

import java.util.*

class Lesson150366_2 {
    private val parents = IntArray(2501) { it }
    private val words = Array(2501) { "" }

    fun solution(commands: Array<String>): Array<String> {
        val result = mutableListOf<String>()

        commands.forEach { command ->
            val st = StringTokenizer(command)

            when (st.nextToken()) {
                "UPDATE" -> {
                    if (st.countTokens() == 2) {
                        val old = st.nextToken()
                        val new = st.nextToken()
                        words.forEachIndexed { index, word -> if (word == old) words[index] = new }
                    } else if (st.countTokens() == 3) {
                        val pIndex = getIndex(st.nextPosition())
                        val wIndex = find(pIndex)
                        words[wIndex] = st.nextToken()
                    }
                }

                "MERGE" -> {
                    val (r1, c1) = st.nextPosition()
                    val (r2, c2) = st.nextPosition()
                    val wIndex1 = find(getIndex(r1 to c1))
                    val wIndex2 = find(getIndex(r2 to c2))

                    if (wIndex1 == wIndex2) return@forEach

                    val word = words[wIndex1].ifBlank { words[wIndex2] }

                    words[wIndex1] = ""
                    words[wIndex2] = ""
                    union(wIndex1, wIndex2)
                    words[find(getIndex(r1 to c1))] = word
                }

                "UNMERGE" -> {
                    val p = st.nextPosition()
                    val wIndex = find(getIndex(p))
                    val target = words[wIndex]

                    words[wIndex] = ""
                    words[getIndex(p)] = target

                    parents.indices
                        .filter { wIndex == find(it) }
                        .forEach { parents[it] = it }
                }

                "PRINT" -> {
                    val pIndex = getIndex(st.nextPosition())
                    val wIndex = find(pIndex)
                    result.add(words[wIndex].ifBlank { "EMPTY" })
                }
            }
        }

        return result.toTypedArray()
    }

    private fun union(a: Int, b: Int) {
        val first = find(a)
        val second = find(b)
        if (first < second) parents[second] = first else parents[first] = second
    }

    private fun find(n: Int): Int = if (parents[n] == n) n else find(parents[n])

    private fun StringTokenizer.nextPosition(): Pair<Int, Int> = Pair(nextToken().toInt(), nextToken().toInt())

    private fun getIndex(p: Pair<Int, Int>): Int = (p.first - 1) * 50 + p.second
}
