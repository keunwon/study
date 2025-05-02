package algorithm.leetcode

class `43_Multiply_Strings` {
    fun multiply(num1: String, num2: String): String {
        val arr = IntArray(num1.length + num2.length)
        for (i in num1.lastIndex downTo 0) {
            for (j in num2.lastIndex downTo 0) {
                val n1 = num1[i] - '0'
                val n2 = num2[j] - '0'
                arr[i + j + 1] += n1 * n2
            }
        }

        var carry = 0
        for (i in arr.lastIndex downTo 0) {
            val sum = arr[i] + carry
            arr[i] = sum % 10
            carry = sum / 10
        }

        return buildString(arr.size) {
            for (n in arr) {
                if (isEmpty() && n == 0) continue
                append(n)
            }
            ifBlank { "0" }.toString()
        }
    }
}

fun main() {
    `43_Multiply_Strings`().multiply("2", "3").also { println(it) }
    `43_Multiply_Strings`().multiply("123", "456").also { println(it) }
}
