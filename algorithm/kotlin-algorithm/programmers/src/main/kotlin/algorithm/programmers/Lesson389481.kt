package algorithm.programmers

/**
 * <p>
 * 이름: 봉인된 주문
 * 난이도: Level-3
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/389481"> (Level-3)</a>
 **/
class Lesson389481 {
    fun solution(n: Long, bans: Array<String>): String {
        val banBase26 = bans.map { it.toBase26() }.sorted()
        var target = n

        for (base26 in banBase26) {
            if (base26 > target) break
            ++target
        }

        return target.toStringBase26()
    }

    private fun Long.toStringBase26(): String {
        var target = this
        val sb = StringBuilder()

        while (target > 0) {
            val letter = 'a' + (--target % 26).toInt()
            sb.append(letter)
            target /= 26
        }

        return sb.reverse().toString()
    }

    private fun String.toBase26(): Long =
        fold(0L) { acc, c -> acc * 26 + (c - 'a') + 1 }
}
