package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 애너그램
 * Level: 골드-5
 **/
class Problem6443 {
    private val alphabets = IntArray(26)
    private val stack = Stack<Char>()
    private val answer = mutableSetOf<String>()

    fun solution(word: String): List<String> {
        word.forEach { c -> alphabets[c - 'a']++ }
        dfs(word.length)
        return answer.sorted()
    }

    private fun dfs(size: Int) {
        if (size == 0) {
            answer.add(stack.joinToString(""))
            return
        }

        for ((i, count) in alphabets.withIndex()) {
            if (count == 0) continue

            alphabets[i]--
            stack.push(Char(i + 'a'.code))

            dfs(size - 1)

            stack.pop()
            alphabets[i]++
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    repeat(readLine().toInt()) {
        val word = readLine()

        Problem6443().solution(word).forEach {
            bw.write(it)
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    close()
}
