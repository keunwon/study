package com.keunwon.algorithm.programmers

class Lesson77886 {
    fun solution(s: Array<String>): Array<String> = s.map { replace(it) }.toTypedArray()

    private fun replace(text: String): String {
        val sb = StringBuilder()
        var count = 0

        for (c in text) {
            sb.append(c)
            if (sb.length < 3) continue

            val lastIndex = sb.lastIndex
            if (sb[lastIndex - 2] == '1' && sb[lastIndex - 1] == '1' && sb[lastIndex] == '0') {
                sb.delete(lastIndex - 2, sb.length)
                ++count
            }
        }
        return sb.apply {
            val index = indexOfLast { it == '0' } + 1
            repeat(count) { insert(index, "110") }
        }.toString()
    }
}
