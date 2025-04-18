package algorithm.programmers

class Lesosn42890_2 {
    private lateinit var relation: Array<Array<String>>
    private val candidates = hashSetOf<String>()

    fun solution(relation: Array<Array<String>>): Int {
        this.relation = relation
        val visited = BooleanArray(relation[0].size)

        for (size in 1..relation[0].size) {
            permutation(0, 0, IntArray(size), visited)
        }
        return candidates.size
    }

    private fun permutation(
        depth: Int,
        sIndex: Int,
        picks: IntArray,
        visited: BooleanArray,
    ) {
        if (depth == picks.size) {
            val data = hashSetOf<String>()
            relation.forEach { r ->
                val key = picks.joinToString("") { r[it] }
                if (data.contains(key)) return
                data.add(key)
            }

            val key = picks.joinToString("")
            candidates.forEach { candi ->
                val count = candi.count { key.contains(it) }
                if (count == candi.length) return
            }

            candidates.add(key)
            return
        }

        for (i in sIndex until visited.size) {
            if (!visited[i]) {
                visited[i] = true
                picks[depth] = i
                permutation(depth + 1, i + 1, picks, visited)
                visited[i] = false
            }
        }
    }
}
