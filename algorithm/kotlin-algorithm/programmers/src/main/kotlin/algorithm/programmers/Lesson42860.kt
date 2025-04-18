package algorithm.programmers

class Lesson42860 {
    fun solution(name: String): Int {
        var move = name.lastIndex
        var change = 0

        for ((i, c) in name.withIndex()) {
            change += c.changeCount()

            var tIndex = i + 1
            while (tIndex < name.length && name[tIndex] == 'A') {
                tIndex++
            }

            val diff = name.length - tIndex
            move = move
                .coerceAtMost(diff * 2 + i)
                .coerceAtMost(i * 2 + diff)
        }
        return move + change
    }

    private fun Char.changeCount(): Int {
        val c1 = this - 'A'
        val c2 = 'Z' - this + 1
        return c1.coerceAtMost(c2)
    }
}
