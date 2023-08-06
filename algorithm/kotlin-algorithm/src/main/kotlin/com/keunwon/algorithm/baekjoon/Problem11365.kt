package com.keunwon.algorithm.baekjoon

/**
 * Title: !밀비 급일
 * Level: 브론즈-4
 **/
class Problem11365 {
    fun solution() {
        val content = mutableListOf<String>()
        while (true) {
            val text = readLine()!!

            if (text == "END") break
            content.add(text)
        }
        content.forEach { println(it.reversed()) }
    }
}

fun main() {
    Problem11365().solution()
}
