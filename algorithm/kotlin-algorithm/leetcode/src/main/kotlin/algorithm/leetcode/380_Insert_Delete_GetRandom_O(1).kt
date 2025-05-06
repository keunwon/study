package algorithm.leetcode

class `380_Insert_Delete_GetRandom_O(1)` {
    private val PRESENT = Any()
    private val map = mutableMapOf<Int, Any>()

    fun insert(`val`: Int): Boolean {
        if (!map.contains(`val`)) return false
        map[`val`] = PRESENT
        return true
    }

    fun remove(`val`: Int): Boolean {
        return map.remove(`val`, PRESENT)
    }

    fun getRandom(): Int {
        return map.keys.random()
    }
}
