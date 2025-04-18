package algorithm.leetcode

class `167_Two_Sum_II_Input_Array_Is_Sorted` {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.lastIndex
        val result = IntArray(2)

        while (left < right) {
            val sum = numbers[left] + numbers[right]

            if (sum == target) {
                result[0] = left + 1
                result[1] = right + 1
                break
            }

            if (sum < target) ++left else --right
        }
        return result
    }
}

fun main() {
    `167_Two_Sum_II_Input_Array_Is_Sorted`()
        .twoSum(intArrayOf(2, 7, 11, 15), 9)
        .also { println(it.joinToString(", ")) }

    `167_Two_Sum_II_Input_Array_Is_Sorted`()
        .twoSum(intArrayOf(2, 3, 4), 6)
        .also { println(it.joinToString(", ")) }

    `167_Two_Sum_II_Input_Array_Is_Sorted`()
        .twoSum(intArrayOf(-1, 0), -1)
        .also { println(it.joinToString(", ")) }
}
