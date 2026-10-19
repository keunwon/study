class Solution {
    fun romanToInt(s: String): Int {
        val point = IntArray(26).apply {
            this['I' - 'A'] = 1
            this['V' - 'A'] = 5
            this['X' - 'A'] = 10
            this['L' - 'A'] = 50
            this['C' - 'A'] = 100
            this['D' - 'A'] = 500
            this['M' - 'A'] = 1000
        }
        var res = point[s[0] - 'A'];

        for (i in 1 until s.length) {
            val p1 = point[s[i - 1] - 'A']
            val p2 = point[s[i] - 'A']

            res += if (p2 > p1) {
                p2 - p1 - p1
            } else {
                p2
            }
        }
        return res
    }
}