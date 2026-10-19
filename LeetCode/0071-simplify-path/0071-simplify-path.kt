class Solution {
    fun simplifyPath(path: String): String {
        val tokens = path.split("/").filter { it.isNotBlank() }
        val stk = ArrayDeque<String>()

        for (t in tokens) {
            if (t == "..") {
                if (stk.isNotEmpty()) stk.removeLast()
            } else if (t.isNotEmpty() && t != ".") {
                stk.addLast(t)
            }
        }
        return stk.joinToString("/", "/")
    }
}