class Solution {
    fun evalRPN(tokens: Array<String>): Int {
        val stk = IntArray(tokens.size)
        var top = -1

        for (t in tokens) {
            when (t) {
                "+" -> {
                    stk[top - 1] = stk[top - 1] + stk[top]
                    --top
                }

                "-" -> {
                    stk[top - 1] = stk[top - 1] - stk[top]
                    --top
                }

                "*" -> {
                    stk[top - 1] = stk[top - 1] * stk[top]
                    --top
                }
                
                "/" -> {
                    stk[top - 1] = stk[top - 1]  / stk[top]
                    --top
                }

                else -> stk[++top] = t.toInt()
            }
        }
        return stk[0]
    }
}