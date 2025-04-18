package algorithm.leetcode

class `2325_Decode_the_Message` {
    fun decodeMessage(key: String, message: String): String {
        val map = mutableMapOf(' ' to ' ')
        var cur = 'a'

        for (c in key) {
            if (c !in map) map[c] = cur++
        }
        return message.map { map.getValue(it) }.joinToString("")
    }
}

fun main() {
    `2325_Decode_the_Message`().decodeMessage(
        "the quick brown fox jumps over the lazy dog",
        "vkbs bs t suepuv"
    ).also { println(it) } // this is a secret

    `2325_Decode_the_Message`().decodeMessage(
        "eljuxhpwnyrdgtqkviszcfmabo",
        "zwx hnfx lqantp mnoeius ycgk vcnjrdb"
    ).also { println(it) } // the five boxing wizards jump quickly
}
