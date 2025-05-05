package algorithm.leetcode

class `705_Design_HashSet` {
    private val table = Array(1e4.toInt()) { mutableListOf<Int>() }

    fun add(key: Int) {
        val idx = key % table.size
        table[idx].add(key)
    }

    fun remove(key: Int) {
        val idx = key % table.size
        val list = table[idx].ifEmpty { return }
        list.remove(key)
    }

    fun contains(key: Int): Boolean {
        val idx = key % table.size
        return table[idx].contains(key)
    }
}
