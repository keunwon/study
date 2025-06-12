package algorithm.programmers

class Lesson258711 {
    fun solution(edges: Array<IntArray>): IntArray {
        val inDegree = mutableMapOf<Int, Int>()
        val outDegree = mutableMapOf<Int, Int>()
        val result = IntArray(4)

        for ((a, b) in edges) {
            outDegree[a] = outDegree.getOrDefault(a, 0) + 1
            inDegree[b] = inDegree.getOrDefault(b, 0) + 1
        }

        for ((key, value) in outDegree) {
            if (value > 1) {
                if (!inDegree.containsKey(key)) {
                    result[0] = key
                } else {
                    ++result[3]
                }
            }
        }

        for ((key, _) in inDegree) {
            if (!outDegree.containsKey(key)) ++result[2]
        }

        result[1] = outDegree.getValue(result[0]) - result[2] - result[3]
        return result
    }
}
