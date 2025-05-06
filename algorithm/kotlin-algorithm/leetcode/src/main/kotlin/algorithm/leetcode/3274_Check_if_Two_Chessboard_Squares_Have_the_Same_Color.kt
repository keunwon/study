package algorithm.leetcode

class `3274_Check_if_Two_Chessboard_Squares_Have_the_Same_Color` {
    fun checkTwoChessboards(coordinate1: String, coordinate2: String): Boolean {
        return isOdd(coordinate1) == isOdd(coordinate2)
    }

    private fun isOdd(str: String): Boolean {
        val type = (str[0] - 'a' + 1)
        val n = str[1] - '0'

        return if (type % 2 == 1) {
            n % 2 == 1
        } else {
            n % 2 == 0
        }
    }
}

fun main() {
    `3274_Check_if_Two_Chessboard_Squares_Have_the_Same_Color`().checkTwoChessboards("c2", "g4")
        .also { println(it) }
}
