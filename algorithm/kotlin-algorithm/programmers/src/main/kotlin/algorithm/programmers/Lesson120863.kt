package algorithm.programmers

class Lesson120863 {
    fun solution(polynomial: String): String {
        var xNum = 0
        var defaultValue = 0

        for (str in polynomial.split(" + ")) {
            if (str.last() == 'X') {
                xNum += str.dropLast(1).ifBlank { "1" }.toInt()
            } else {
                defaultValue += str.toInt()
            }
        }

        return buildString {
            if (xNum > 0) {
                if (xNum > 1) append(xNum)
                append('X')
            }
            if (defaultValue > 0) append(defaultValue)
        }
    }
}
