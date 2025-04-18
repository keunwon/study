package algorithm.programmers

/**
 * <p>
 * 이름:홀짝트리
 * 난이도: Level-4
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/388354">홀짝트리 (Level-4)</a>
 **/
class Lesson388354 {
    private lateinit var tree: Map<Int, List<Int>>
    private lateinit var visited: MutableSet<Int>

    private var yellow = 0
    private var red = 0

    fun solution(nodes: IntArray, edges: Array<IntArray>): IntArray {
        this.tree = nodes.associateWith { mutableListOf<Int>() }.apply {
            edges.forEach { (a, b) ->
                getValue(a).add(b)
                getValue(b).add(a)
            }
        }
        this.visited = HashSet(nodes.size)
        val result = IntArray(2)

        for (node in nodes) {
            if (!visited.contains(node)) {
                yellow = 0
                red = 0
                dfs(node)

                // 홀짝 트리, 역홀짝 트리
                if (yellow == 1) ++result[0]
                if (red == 1) ++result[1]
            }
        }
        return result
    }

    private fun dfs(cur: Int) {
        if (visited.contains(cur)) return
        visited.add(cur)

        val list = tree[cur] ?: return
        val isYellow = cur % 2 == list.size % 2

        if (isYellow) ++yellow else ++red

        for (next in list) {
            dfs(next)
        }
    }
}
