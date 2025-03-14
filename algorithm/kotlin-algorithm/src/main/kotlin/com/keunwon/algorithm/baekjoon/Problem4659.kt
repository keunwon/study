package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 비밀번호 발음하기
 * 난이도: 실버-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/4659">비밀번호 발음하기 (실버-5)</a>
 **/
class Problem4659 {
    fun solution(words: List<String>): Array<String> {
        val result = Array(words.size) { "" }
        val moeum = charArrayOf('a', 'e', 'i', 'o', 'u')

        for ((i, word) in words.withIndex()) {
            var flag = true

            if (word.none { moeum.contains(it) }) {
                flag = false
            }

            if (flag) {
                for (j in 2 until word.length) {
                    val arr = charArrayOf(word[j - 2], word[j - 1], word[j])
                    if (arr.all { moeum.contains(it) }) {
                        flag = false
                        break
                    } else if (arr.all { !moeum.contains(it) }) {
                        flag = false
                        break
                    }
                }
            }

            if (flag) {
                for (j in 1 until word.length) {
                    val tmp = "${word[j - 1]}${word[j]}"
                    if (tmp != "ee" && tmp != "oo" && tmp[0] == tmp[1]) {
                        flag = false
                        break
                    }
                }
            }

            val message = if (flag) "acceptable" else "not acceptable"
            result[i] = "<${word}> is $message."
        }

        return result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val words = mutableListOf<String>()
    while (true) {
        val word = br.readLine()
        if (word == "end") break
        words.add(word)
    }

    Problem4659().solution(words).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}
