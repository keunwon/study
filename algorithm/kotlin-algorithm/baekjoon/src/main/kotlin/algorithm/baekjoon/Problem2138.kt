package algorithm.baekjoon

/**
 * <p>
 * 이름: 전구와 스위치
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2138">전구와 스위치 (골드-4)</a>
 **/
class Problem2138 {
    fun solution(src: String, dest: String): Int {
        val light1 = BooleanArray(src.length) { src[it] == '1' }
        val light2 = BooleanArray(src.length) { src[it] == '1' }.apply { press(this, 0) }
        val destLight = BooleanArray(dest.length) { dest[it] == '1' }
        val result = dfs(1, 0, light1, destLight)
            .coerceAtMost(dfs(1, 1, light2, destLight))
        return if (result == 1e9.toInt()) -1 else result
    }

    private fun dfs(depth: Int, count: Int, src: BooleanArray, dest: BooleanArray): Int {
        if (depth == src.size) {
            return if (src[depth - 1] == dest[depth - 1]) count else 1e9.toInt()
        }

        var result = 1e9.toInt()
        if (src[depth - 1] == dest[depth - 1]) {
            result = result.coerceAtMost(dfs(depth + 1, count, src, dest))
        } else {
            press(src, depth)
            result = result.coerceAtMost(dfs(depth + 1, count + 1, src, dest))
        }
        return result
    }

    private fun press(light: BooleanArray, n: Int) {
        for (i in n - 1..n + 1) {
            if (i in light.indices) light[i] = !light[i]
        }
    }
}

fun main() {
    val n = readln().toInt()
    val src = readln()
    val dest = readln()
    Problem2138().solution(src, dest).also { println(it) }
}
