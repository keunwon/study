package algorithm.baekjoon

/**
 * <p>
 * 이름: 어른 상어
 * 난이도: 골드-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/19237">어른 상어 (골드-2)</a>
 **/
class Problem19237 {
    //  1, 2, 3, 4는 각각 위, 아래, 왼쪽, 오른쪽을 의미한다.
    private val moves = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

    fun solution(
        k: Int,
        map: Array<IntArray>,
        initSharkDirs: IntArray,
        priorityDirs: Array<Array<IntArray>>,
    ): Int {
        val sharks = mutableListOf<Shark>()
        val smell = Array(map.size) { Array(map[0].size) { IntArray(2) } }

        for (i in map.indices) {
            for ((j, id) in map[i].withIndex()) {
                if (id > 0) {
                    sharks.add(Shark(id, i, j, initSharkDirs[id - 1] - 1))
                    smell[i][j][0] = id
                    smell[i][j][1] = k
                }
            }
        }
        sharks.sortBy { it.id }

        var time = 0
        while (time++ < 1000) {
            loop@ for (shark in sharks) {
                val sharkDirs = priorityDirs[shark.id - 1]

                for (sharkDir in sharkDirs[shark.dir]) {
                    val dir = sharkDir - 1
                    val nr = shark.r + moves[dir].first
                    val nc = shark.c + moves[dir].second

                    if (nr in map.indices && nc in map[0].indices && smell[nr][nc][0] == 0) {
                        shark.r = nr
                        shark.c = nc
                        shark.dir = dir
                        continue@loop
                    }
                }

                for (sharkDir in sharkDirs[shark.dir]) {
                    val dir = sharkDir - 1
                    val nr = shark.r + moves[dir].first
                    val nc = shark.c + moves[dir].second

                    if (nr in map.indices && nc in map[0].indices && smell[nr][nc][0] == shark.id) {
                        shark.r = nr
                        shark.c = nc
                        shark.dir = dir
                        continue@loop
                    }
                }
            }

            for (i in smell.indices) {
                for (j in smell[i].indices) {
                    val (id, num) = smell[i][j]
                    if (id > 0) {
                        --smell[i][j][1]
                        if (num == 1) smell[i][j][0] = 0
                    }
                }
            }

            val eatSharkIds = mutableSetOf<Int>()
            for (shark in sharks) {
                val id = smell[shark.r][shark.c][0]
                if (id == 0 || id == shark.id) {
                    smell[shark.r][shark.c][0] = shark.id
                    smell[shark.r][shark.c][1] = k
                } else if (id < shark.id) {
                    eatSharkIds.add(shark.id)
                }
            }

            sharks.removeIf { it.id in eatSharkIds }
            if (sharks.size == 1) break
        }

        return if (time > 1000) -1 else time
    }

    private class Shark(val id: Int, var r: Int, var c: Int, var dir: Int)
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
    val map = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }
    val sharkDirs = run {
        val arr = br.readLine().split(" ")
        IntArray(m) { arr[it].toInt() }
    }
    val priorityDirs = Array(m) {
        Array(4) {
            val arr = br.readLine().split(" ")
            IntArray(4) { arr[it].toInt() }
        }
    }

    Problem19237().solution(k, map, sharkDirs, priorityDirs).also(::println)
}
