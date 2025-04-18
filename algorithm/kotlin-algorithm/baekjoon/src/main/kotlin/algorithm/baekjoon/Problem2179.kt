package algorithm.baekjoon

/**
 * <p>
 * 이름: 비슷한 단어
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2179">비슷한 단어 (골드-4)</a>
 **/
class Problem2179 {
    fun solution(words: Array<String>): Array<String> {
        var idx1 = 0
        var idx2 = 0
        var max = 0

        for (i in words.indices) {
            val target = words[i]

            for (j in i + 1 until words.size) {
                val word = words[j]
                val size = target.length.coerceAtMost(word.length)
                var count = 0

                for (k in 0 until size) {
                    if (target[k] != word[k]) break
                    ++count
                }

                if (max < count) {
                    max = count
                    idx1 = i
                    idx2 = j
                }
            }
        }
        return arrayOf(words[idx1], words[idx2])
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val words = Array(n) { br.readLine() }
    Problem2179().solution(words).forEach { println(it) }
}
