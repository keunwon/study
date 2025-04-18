package algorithm.programmers

import java.util.*

class Lesson150366 {
    private val parents = IntArray(2501) { it }
    private val words = Array(2501) { "" }

    fun solution(commands: Array<String>): Array<String> {
        val answer = mutableListOf<String>()
        for (command in commands) {
            val st = StringTokenizer(command)

            when (st.nextToken()) {
                "UPDATE" -> {
                    if (st.countTokens() == 2) {
                        val (old, new) = Array(2) { st.nextToken() }
                        words.forEachIndexed { index, word -> if (word == old) words[index] = new }
                    } else if (st.countTokens() == 3) {
                        val (r, c) = IntArray(2) { st.nextToken().toInt() }
                        val wIndex = find(getParentsIndex(r, c))
                        words[wIndex] = st.nextToken()
                    }
                }

                "MERGE" -> {
                    val (r1, c1, r2, c2) = IntArray(4) { st.nextToken().toInt() }
                    val wIndex1 = find(getParentsIndex(r1, c1))
                    val wIndex2 = find(getParentsIndex(r2, c2))

                    if (wIndex1 == wIndex2) continue

                    val word = words[wIndex1].ifBlank { words[wIndex2] }

                    words[wIndex1] = ""
                    words[wIndex2] = ""
                    union(wIndex1, wIndex2)
                    words[find(getParentsIndex(r1, c1))] = word
                }

                "UNMERGE" -> {
                    val (r, c) = IntArray(2) { st.nextToken().toInt() }
                    val wIndex = find(getParentsIndex(r, c))
                    val word = words[wIndex]

                    words[wIndex] = ""
                    words[getParentsIndex(r, c)] = word

                    parents.indices
                        .filter { wIndex == find(it) }
                        .forEach { parents[it] = it }
                }

                "PRINT" -> {
                    val (r, c) = IntArray(2) { st.nextToken().toInt() }
                    val wIndex = find(getParentsIndex(r, c))
                    answer.add(words[wIndex].ifBlank { "EMPTY" })
                }
            }
        }
        return answer.toTypedArray()
    }

    private fun find(n: Int): Int = if (parents[n] == n) n else find(parents[n])

    private fun union(a: Int, b: Int) {
        val first = find(a)
        val second = find(b)

        if (first < second) parents[second] = first
        else parents[first] = second
    }

    private fun getParentsIndex(r: Int, c: Int) = (r - 1) * 50 + c
}
