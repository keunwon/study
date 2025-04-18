package algorithm.baekjoon

/**
 * <p>
 * 이름: 틱택토
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/7682">틱택토 (골드-5)</a>
 **/
class Problem7682 {
    fun solution(line: String): String {
        val xCount = line.count { it == 'X' }
        val oCount = line.count { it == 'O' }
        val valid = "valid"
        val invalid = "invalid"

        return if (xCount == oCount && !check(line, 'X') && check(line, 'O')) {
            valid
        } else if (xCount == oCount + 1) {
            if (xCount + oCount == 9 && !check(line, 'O')) valid
            else if (!check(line, 'O') && check(line, 'X')) valid
            else invalid
        } else {
            invalid
        }
    }

    private fun check(line: String, winType: Char): Boolean {
        for (i in 0 until 3) {
            var flag = true
            for (j in 0 until 3) {
                if (line[i * 3 + j] != winType) {
                    flag = false
                    break
                }
            }
            if (flag) return true
        }

        for (i in 0 until 3) {
            var flag = true
            for (j in i until line.length step 3) {
                if (line[j] != winType) {
                    flag = false
                    break
                }
            }
            if (flag) return true
        }

        return charArrayOf(line[0], line[4], line[8]).all { it == winType } ||
                charArrayOf(line[2], line[4], line[6]).all { it == winType }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    while (true) {
        val line = br.readLine()
        if (line == "end") break
        Problem7682().solution(line).also {
            bw.write(it)
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    br.close()
}
