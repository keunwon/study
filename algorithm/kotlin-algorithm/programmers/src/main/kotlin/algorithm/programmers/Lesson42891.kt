package algorithm.programmers

class Lesson42891 {
    fun solution(food_times: IntArray, k: Long): Int {
        val foods = food_times.mapIndexed { index, food -> Food(index + 1, food.toLong()) }
            .sortedBy { it.time }
        var prevTime = 0L
        var ret = k

        for ((i, food) in foods.withIndex()) {
            val diff = food.time - prevTime
            val size = foods.size - i

            if (diff != 0L) {
                val total = diff * size
                if (total <= ret) {
                    ret -= total
                    prevTime = food.time
                } else {
                    ret %= size
                    return foods.slice(i until foods.size).sortedBy { it.index }[ret.toInt()].index
                }
            }
        }
        return -1
    }

    private data class Food(val index: Int, var time: Long)
}
