package com.keunwon.algorithm.leetcode

class `6_Zigzag_Conversion` {
    fun convert(s: String, numRows: Int): String {
        val list = Array(numRows) { mutableListOf<Char>() }
        var idx = 0
        var d = 0

        for (c in s) {
            list[idx].add(c)

            if (idx == 0) d = 1
            else if (idx == numRows - 1) d = -1
            idx += d
        }
        return list.joinToString("") { it.joinToString("") }
    }
}

fun main() {
    `6_Zigzag_Conversion`().convert("PAYPALISHIRING", 3)
        .also { println(it) } // PAHNAPLSIIGYIR

    `6_Zigzag_Conversion`().convert("PAYPALISHIRING", 4)
        .also { println(it) } // PINALSIGYAHRPI
}
