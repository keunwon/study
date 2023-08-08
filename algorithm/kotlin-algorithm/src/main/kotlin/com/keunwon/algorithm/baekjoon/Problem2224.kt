package com.keunwon.algorithm.baekjoon

/**
 * Title: 명제 증명
 * Level: 골드-4
 **/
class Problem2224 {
    fun solution(arr: Array<Pair<Char, Char>>): Array<Pair<Char, Char>> {
        val alphabets = Array(52) { BooleanArray(52) }.apply {
            arr.forEach { (c1, c2) ->
                this[c1.toIndex()][c2.toIndex()] = true
            }
        }

        for (i in alphabets.indices) {
            for (j in alphabets.indices) {
                for (k in alphabets.indices) {
                    if (j == i || i == k || j == k || alphabets[j][k]) continue

                    if (alphabets[j][i] && alphabets[i][k]) {
                        alphabets[j][k] = true
                    }
                }
            }
        }

        val answer = mutableListOf<Pair<Char, Char>>()

        for (i in alphabets.indices) {
            for (j in alphabets.indices) {
                if (i == j || !alphabets[i][j]) continue
                answer.add(i.toAlphabet() to j.toAlphabet())
            }
        }
        return answer.toTypedArray()
    }

    private fun Char.toIndex(): Int {
        return if (this.isUpperCase()) this - 'A' else this - 'a' + 26
    }

    private fun Int.toAlphabet(): Char {
        return if (this < 26) Char(this + 'A'.code) else Char(this + 'a'.code - 26)
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!.replace(" ", "")
            .split("=>")
            .let { it[0][0] to it[1][0] }
    }

    Problem2224().solution(arr).also { arr ->
        println(arr.size)
        arr.forEach { println("${it.first} => ${it.second}") }
    }
}

