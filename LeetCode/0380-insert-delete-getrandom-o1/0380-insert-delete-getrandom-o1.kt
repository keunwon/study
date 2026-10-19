class RandomizedSet() {
    val map = HashMap<Int, Int>()
    val numbers = mutableListOf<Int>()
    val random = Random

    fun insert(`val`: Int): Boolean {
        if (map.contains(`val`)) return false

        map[`val`] = map.size
        return numbers.add(`val`)
    }

    fun remove(`val`: Int): Boolean {
        val removeIdx = map[`val`] ?: return false
        val lastVal = numbers.last()

        numbers[removeIdx] = lastVal
        map[lastVal] = removeIdx
        map.remove(`val`)
        numbers.removeLast()
        return true
    }

    fun getRandom(): Int {
        val id = random.nextInt(numbers.size)
        return numbers[id]
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * var obj = RandomizedSet()
 * var param_1 = obj.insert(`val`)
 * var param_2 = obj.remove(`val`)
 * var param_3 = obj.getRandom()
 */