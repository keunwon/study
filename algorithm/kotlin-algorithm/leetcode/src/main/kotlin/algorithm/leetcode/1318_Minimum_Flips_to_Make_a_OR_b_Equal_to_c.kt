package algorithm.leetcode

class `1318_Minimum_Flips_to_Make_a_OR_b_Equal_to_c` {
    fun minFlips(a: Int, b: Int, c: Int): Int {
        var aa = a
        var bb = b
        var cc = c
        var result = 0

        while (aa > 0 || bb > 0 || cc > 0) {
            result += if (cc and 1 == 1) {
                1 - (aa.and(1) or bb.and(1))
            } else {
                val tmp = aa and 1 + bb and 1
                tmp
            }

            aa = aa shr 1
            bb = bb shr 1
            cc = cc shr 1
        }
        return result
    }
}

fun main() {
    `1318_Minimum_Flips_to_Make_a_OR_b_Equal_to_c`().minFlips(8, 3, 5).also { println(it) }
}
