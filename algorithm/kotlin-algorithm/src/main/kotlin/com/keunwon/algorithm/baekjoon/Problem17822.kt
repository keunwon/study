package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 원판 돌리기
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17822">원판 돌리기 (골드-3)</a>
 **/
class Problem17822 {
    fun solution(points: Array<IntArray>, commands: Array<IntArray>): Int {
        for ((x, d, k) in commands) {
            for (i in x - 1 until points.size step x) {
                rotate(points[i], d, k)
            }
            clean(points)
        }
        return points.sumOf(IntArray::sum)
    }

    private fun clean(points: Array<IntArray>) {
        val visited = Array(points.size) { BooleanArray(points[0].size) }
        var total = 0
        var count = 0
        var flag = false

        for (i in points.indices) {
            for (j in points[i].indices) {
                if (points[i][j] == 0) continue

                total += points[i][j]
                ++count

                if (points[i][j] == points[i][(j + 1) % points[0].size]) {
                    visited[i][j] = true
                    visited[i][(j + 1) % points[0].size] = true
                    flag = true
                }

                if (i < points.lastIndex && points[i][j] == points[i + 1][j]) {
                    visited[i][j] = true
                    visited[i + 1][j] = true
                    flag = true
                }
            }
        }

        if (flag) {
            for (i in visited.indices) {
                for (j in visited[i].indices) {
                    if (visited[i][j]) points[i][j] = 0
                }
            }
        } else {
            val average = total.toDouble() / count
            for (i in points.indices) {
                for (j in points[i].indices) {
                    val num = points[i][j]
                    if (num > 0) {
                        points[i][j] = when {
                            num > average -> num - 1
                            num < average -> num + 1
                            else -> num
                        }
                    }
                }
            }
        }
    }

    private fun rotate(point: IntArray, d: Int, k: Int) {
        val tmpArr = IntArray(point.size)

        if (d == 0) {
            for (i in point.indices) {
                tmpArr[i] = point[(i + point.size - k) % point.size]
            }
        } else if (d == 1) {
            for (i in point.indices) {
                tmpArr[i] = point[(i + k) % point.size]
            }
        }
        System.arraycopy(tmpArr, 0, point, 0, point.size)
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m, t) = br.readLine().split(" ").map { it.toInt() }

    val points = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(m) { arr[it].toInt() }
    }
    val commands = Array(t) {
        val arr = br.readLine().split(" ")
        IntArray(3) { arr[it].toInt() }
    }

    Problem17822().solution(points, commands).also(::println)
}
