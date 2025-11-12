package algorithm.programmers

class Lesson161990 {
    fun solution(wallpaper: Array<String>): IntArray {
        var r1 = wallpaper.lastIndex
        var c1 = wallpaper[0].lastIndex
        var r2 = 0
        var c2 = 0

        for (i in wallpaper.indices) {
            for (j in wallpaper[i].indices) {
                if (wallpaper[i][j] == '#') {
                    r1 = r1.coerceAtMost(i)
                    c1 = c1.coerceAtMost(j)
                    r2 = r2.coerceAtLeast(i)
                    c2 = c2.coerceAtLeast(j)
                }
            }
        }

        return intArrayOf(r1, c1, r2 + 1, c2 + 1)
    }
}
