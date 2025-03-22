package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 단축키 지정
 * 난이도: 실버-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1283">단축키 지정 (실버-1)</a>
 **/
class Problem1283 {
    fun solution(words: Array<String>): Array<String> {
        val alphabet = BooleanArray(26)
        val result = Array(words.size) { "" }

        for (i in words.indices) {
            var target = ' '
            val arr = words[i].split(" ")

            for (str in arr) {
                val aIndex = str[0].uppercaseChar() - 'A'
                if (!alphabet[aIndex]) {
                    alphabet[aIndex] = true
                    target = str[0]
                    break
                }
            }

            if (!target.isWhitespace()) {
                val sb = StringBuilder(words[i].length + 2)
                var flag = false

                for ((j, str) in arr.withIndex()) {
                    if (!flag && str[0] == target) {
                        flag = true
                        sb.append('[').append(str[0]).append(']').append(str.drop(1))
                    } else {
                        sb.append(str)
                    }

                    if (j != arr.lastIndex) sb.append(" ")
                }
                result[i] = sb.toString()
                continue
            }

            for (c in words[i]) {
                val aIndex = c.uppercaseChar() - 'A'
                if (!c.isWhitespace() && !alphabet[aIndex]) {
                    alphabet[aIndex] = true
                    target = c
                    break
                }
            }

            if (!target.isWhitespace()) {
                val sb = StringBuilder(words[i].length + 2)
                var flag = false

                for (c in words[i]) {
                    if (!flag && c == target) {
                        flag = true
                        sb.append('[').append(c).append(']')
                    } else {
                        sb.append(c)
                    }
                }
                result[i] = sb.toString()
                continue
            }

            result[i] = words[i]
        }
        return result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val words = Array(n) { br.readLine() }
    Problem1283().solution(words).forEach {
        bw.write(it)
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}
