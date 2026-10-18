class Solution {
    fun isValid(s: String): Boolean {
        val stk = CharArray(s.length)
        var top = -1

        for (i in s.indices) {
            val c = s[i]
            when (c) {
                '(' -> stk[++top] = ')'
                '[' -> stk[++top] = ']'
                '{' -> stk[++top] = '}'
                else -> {
                    if (top == -1 || stk[top] != c) return false
                    --top
                }
            }
        }
        return top == -1
    }
}