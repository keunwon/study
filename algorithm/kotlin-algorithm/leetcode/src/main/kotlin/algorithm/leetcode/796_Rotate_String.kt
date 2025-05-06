package algorithm.leetcode

class `796_Rotate_String` {
    fun rotateString(s: String, goal: String): Boolean {
        if (s.length != goal.length) return false

        for (i in s.indices) {
            var flag = true
            for (j in s.indices) {
                if (s[(i + j) % s.length] != goal[j]) {
                    flag = false
                    break
                }
            }
            if (flag) return true
        }
        return false
    }
}
