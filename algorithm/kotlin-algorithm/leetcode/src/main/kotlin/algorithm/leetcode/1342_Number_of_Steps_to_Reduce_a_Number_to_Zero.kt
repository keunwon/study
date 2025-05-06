package algorithm.leetcode

class `1342_Number_of_Steps_to_Reduce_a_Number_to_Zero` {
    fun numberOfSteps(num: Int): Int {
        return generateSequence(num) { if (it % 2 == 0) it / 2 else it - 1 }
            .takeWhile { it > 0 }
            .count()
    }
}
