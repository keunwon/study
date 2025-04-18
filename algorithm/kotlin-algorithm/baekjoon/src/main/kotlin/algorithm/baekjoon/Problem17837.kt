package algorithm.baekjoon

/**
 * <p>
 * 이름: 새로운 게임 2
 * 난이도: 골드-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17837">새로운 게임 2 (골드-2)</a>
 **/
class Problem17837 {
    fun solution(map: Array<IntArray>, infos: Array<IntArray>): Int {
        val pieces = Array(infos.size + 1) { Triple(-1, -1, -1) }
        val list = Array(map.size) { Array(map[0].size) { mutableListOf<Int>() } }
        val moves = arrayOf(0 to 1, 0 to -1, -1 to 0, 1 to 0) // →, ←, ↑, ↓
        var count = 0

        infos.forEachIndexed { index, t ->
            val (r, c, d) = t.map { it - 1 }
            pieces[index + 1] = Triple(r, c, d)
            list[r][c].add(index + 1)
        }

        while (count++ < 1000) {
            for (id in 1 until pieces.size) {
                val (r, c) = pieces[id].first to pieces[id].second
                var dir = pieces[id].third
                var nr = r + moves[dir].first
                var nc = c + moves[dir].second

                // map[i][j]: 0:흰색, 1:빨간색, 2:파란색
                if (nr !in map.indices || nc !in map[0].indices || map[nr][nc] == 2) {
                    dir = when (dir) {
                        0 -> 1
                        1 -> 0
                        2 -> 3
                        3 -> 2
                        else -> dir
                    }
                    nr = r + moves[dir].first
                    nc = c + moves[dir].second
                }

                if (nr in map.indices && nc in map[0].indices) {
                    val cur = list[r][c]
                    val moveIds = cur.slice(cur.indexOf(id) until cur.size)

                    if (map[nr][nc] == 2) {
                        pieces[moveIds[0]] = pieces[moveIds[0]].copy(third = dir)
                        continue
                    }

                    cur.removeAll(moveIds)
                    list[nr][nc].addAll(if (map[nr][nc] == 1) moveIds.reversed() else moveIds)

                    for (i in moveIds.indices) {
                        val mId = moveIds[i]
                        if (i == 0) pieces[mId] = Triple(nr, nc, dir)
                        else pieces[mId] = Triple(nr, nc, pieces[mId].third)
                    }

                    if (list[nr][nc].size >= 4) return count
                }
            }
        }

        return -1
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }
    val infos = Array(k) {
        val arr = br.readLine().split(" ")
        IntArray(3) { arr[it].toInt() }
    }

    Problem17837().solution(map, infos).also(::println)
}
