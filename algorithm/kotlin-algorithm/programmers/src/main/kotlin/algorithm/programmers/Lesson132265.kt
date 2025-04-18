package algorithm.programmers

class Lesson132265 {
    fun solution(topping: IntArray): Int {
        val countMap = topping.toList()
            .groupingBy { it }
            .eachCount()
            .toMutableMap()
        val set = mutableSetOf<Int>()

        return topping.count { n ->
            set.add(n)
            countMap[n] = countMap.getValue(n) - 1
            if (countMap.getValue(n) == 0) countMap.remove(n)
            countMap.size == set.size
        }
    }
}
