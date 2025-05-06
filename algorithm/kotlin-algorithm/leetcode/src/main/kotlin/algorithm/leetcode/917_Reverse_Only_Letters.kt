package algorithm.leetcode

class `917_Reverse_Only_Letters` {
    fun reverseOnlyLetters(s: String): String {
        var left = 0
        var right = s.lastIndex
        val arr = s.toCharArray()

        while (left < right) {
            if (!arr[left].isLetter()) {
                ++left
            } else if (!arr[right].isLetter()) {
                --right
            } else {
                val tmp = arr[left]
                arr[left] = arr[right]
                arr[right] = tmp
                ++left
                --right
            }
        }
        return arr.concatToString()
    }
}
