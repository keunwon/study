package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 가희와 키워드
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/22233">가희와 키워드 (실버-3)</a>
 **/
class Problem22233 {
    fun solution(keywords: Array<String>, posts: Array<String>): IntArray {
        val set = keywords.toMutableSet()
        return IntArray(posts.size) { index ->
            posts[index].split(",").forEach(set::remove)
            set.size
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val keywords = Array(n) { br.readLine() }
    val posts = Array(m) { br.readLine() }

    Problem22233().solution(keywords, posts).forEach {
        bw.write("$it")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    br.close()
}
