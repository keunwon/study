package algorithm.leetcode

class `12_Integer_to_Roman` {
    fun intToRoman(num: Int): String {
        val map = mapOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I",
        )
        var target = num

        return buildString {
            map.forEach { (value, symbol) ->
                while (target >= value) {
                    append(symbol)
                    target -= value
                }
            }
        }
    }
}

fun main() {
    `12_Integer_to_Roman`().intToRoman(3749).also { println(it) } // MMMDCCXLIX
}
