package algorithm.programmers

class Lesson160586 {
    fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
        val inf = 1e9.toInt()
        val alphabet = IntArray(26) { inf }

        for (key in keymap) {
            for ((i, c) in key.withIndex()) {
                alphabet[c - 'A'] = alphabet[c - 'A'].coerceAtMost(i + 1)
            }
        }

        return IntArray(targets.size) { index ->
            val target = targets[index]
            if (target.any { alphabet[it - 'A'] == inf }) {
                -1
            } else {
                target.sumOf { alphabet[it - 'A'] }
            }
        }
    }
}
