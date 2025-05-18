package algorithm.programmers

/**
 * <p>
 * 이름: 시험장 나누기
 * 난이도: Level-5
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/81305?language=kotlin">시험장 나누기 (Level-5)</a>
 **/
class Lesson81305 {
    private lateinit var num: IntArray
    private lateinit var parent: IntArray
    private lateinit var left: IntArray
    private lateinit var right: IntArray

    private var count = 0

    fun solution(k: Int, num: IntArray, links: Array<IntArray>): Int {
        this.num = num
        this.parent = IntArray(num.size) { -1 }
        this.left = IntArray(num.size)
        this.right = IntArray(num.size)

        links.forEachIndexed { index, (a, b) ->
            left[index] = a
            right[index] = b
            if (a != -1) parent[a] = index
            if (b != -1) parent[b] = index
        }

        val root = parent.indexOfFirst { it == -1 }
        var low = num.maxOf { it }
        var high = 1e9.toInt()

        while (low <= high) {
            val mid = (low + high) / 2
            count = 0
            dfs(root, mid)

            if (count < k) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return low
    }

    private fun dfs(cur: Int, max: Int): Int {
        var l = 0
        var r = 0

        if (left[cur] != -1) l += dfs(left[cur], max)
        if (right[cur] != -1) r += dfs(right[cur], max)

        if (num[cur] + l + r <= max) {
            return num[cur] + l + r
        }

        val min = l.coerceAtMost(r)
        if (num[cur] + min <= max) {
            ++count
            return num[cur] + min
        }

        count += 2
        return num[cur]
    }
}
