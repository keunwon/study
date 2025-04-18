package algorithm.baekjoon

/**
 * <p>
 * 이름: 문자열 게임 2
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/20437">문자열 게임 2 (골-5)</a>
 **/
class Problem20437 {
    fun solution(w: String, k: Int): IntArray {
        if (k == 1) return intArrayOf(1, 1)

        val result = intArrayOf(1e9.toInt(), 0)
        val alphabet = IntArray(26).apply { w.forEach { ++this[it - 'a'] } }

        for ((i, c) in w.withIndex()) {
            if (alphabet[c - 'a'] < k) continue

            var count = 1
            for (j in i + 1 until w.length) {
                if (c == w[j]) ++count
                if (count == k) {
                    val length = j - i + 1
                    result[0] = result[0].coerceAtMost(length)
                    result[1] = result[1].coerceAtLeast(length)
                    break
                }
            }
        }
        return if (result[0] == 1e9.toInt() && result[1] == 0) intArrayOf(-1) else result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val t = br.readLine().toInt()
    repeat(t) {
        val w = br.readLine()
        val k = br.readLine().toInt()

        Problem20437().solution(w, k).also {
            bw.write(it.joinToString(" "))
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    br.close()
}
