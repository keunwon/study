package com.keunwon.algorithm.programmers

/**
 * Title:바탕화면 정리
 * Level: 1
 **/
class Lessons161990 {
    fun solution(wallpaper: Array<String>): IntArray {
        var minY = Int.MAX_VALUE
        var maxY = Int.MIN_VALUE
        var minX = Int.MAX_VALUE
        var maxX = Int.MIN_VALUE

        for (i in wallpaper.indices) {
            for (j in wallpaper[0].indices) {
                if (wallpaper[i][j] == '#') {
                    minY = minY.coerceAtMost(i)
                    maxY = maxY.coerceAtLeast(i)
                    minX = minX.coerceAtMost(j)
                    maxX = maxX.coerceAtLeast(j)
                }
            }
        }
        return intArrayOf(minY, minX, maxY + 1, maxX + 1)
    }
}
