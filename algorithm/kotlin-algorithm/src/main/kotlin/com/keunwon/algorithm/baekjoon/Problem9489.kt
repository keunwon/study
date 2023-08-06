package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 사촌
 * Level: 골드-4
 **/
class Problem9489 {
    fun solution() {
        while (true) {
            val (n, k) = readLine()!!.split(" ").map { it.toInt() }
            if (n == 0 && k == 0) break
            val numbers = IntArray(n + 1) { -1 }
            val parent = IntArray(n + 1) { -1 }
            var depth = -1
            var kIndex = -1

            val st = StringTokenizer(readLine())
            for (i in 1..n) {
                numbers[i] = st.nextToken().toInt()

                if (numbers[i] == k) kIndex = i
                if (numbers[i] != numbers[i - 1] + 1) depth++
                parent[i] = depth
            }

            val answer = (1..n).count {
                parent[it] != parent[kIndex] && parent[parent[it]] == parent[parent[kIndex]]
            }
            println(answer)
        }
    }
}

fun main() {
    Problem9489().solution()
}
