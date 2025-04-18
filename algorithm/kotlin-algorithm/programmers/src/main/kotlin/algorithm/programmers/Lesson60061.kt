package algorithm.programmers

class Lesson60061 {
    private lateinit var bos: Array<BooleanArray>
    private lateinit var pillars: Array<BooleanArray>

    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        this.bos = Array(n + 1) { BooleanArray(n + 1) }
        this.pillars = Array(n + 1) { BooleanArray(n + 1) }

        for ((x, y, a, b) in build_frame) {
            when (b) {
                CREATED -> {
                    if (a == PILLAR && creatablePillar(y, x)) pillars[y][x] = true
                    else if (a == BO && creatableBo(y, x)) bos[y][x] = true
                }

                DELETED -> {
                    val arr = if (a == PILLAR) pillars else bos
                    arr[y][x] = false
                    if (!deletable(n)) arr[y][x] = true
                }
            }
        }

        val answer = mutableListOf<IntArray>()
        for (y in 0..n) {
            for (x in 0..n) {
                if (pillars[y][x]) answer.add(intArrayOf(x, y, PILLAR))
                if (bos[y][x]) answer.add(intArrayOf(x, y, BO))
            }
        }
        return answer.sortedWith(compareBy({ it[0] }, { it[1] })).toTypedArray()
    }

    private fun deletable(n: Int): Boolean {
        for (y in 0..n) {
            for (x in 0..n) {
                if (pillars[y][x] && !creatablePillar(y, x)) return false
                if (bos[y][x] && !creatableBo(y, x)) return false
            }
        }
        return true
    }

    private fun creatableBo(y: Int, x: Int): Boolean = when {
        x > 0 && bos[y][x - 1] && bos[y][x + 1] -> true
        y > 0 && pillars[y - 1][x] || pillars[y - 1][x + 1] -> true
        else -> false
    }

    private fun creatablePillar(y: Int, x: Int): Boolean = when {
        y == 0 -> true
        y > 0 && pillars[y - 1][x] -> true
        x > 0 && bos[y][x - 1] || bos[y][x] -> true
        else -> false
    }

    companion object {
        private const val PILLAR = 0
        private const val BO = 1
        private const val DELETED = 0
        private const val CREATED = 1
    }
}
