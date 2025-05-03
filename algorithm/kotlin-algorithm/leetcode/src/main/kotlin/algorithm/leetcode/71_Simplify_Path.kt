package algorithm.leetcode

import java.util.Stack

class `71_Simplify_Path` {
    fun simplifyPath(path: String): String {
        val arr = path.split("/").filter { it.isNotBlank() }
        val stack = Stack<String>()

        for (str in arr) {
            if (str == ".") continue

            if (str == "..") {
                if (stack.isNotEmpty()) stack.pop()
            } else {
                stack.push(str)
            }
        }
        return stack.joinToString("/", "/")
    }
}

fun main() {
    `71_Simplify_Path`().simplifyPath("/home/").also { println(it) } // "/home"
    `71_Simplify_Path`().simplifyPath("/home//foo/").also { println(it) } // "/home/foo"
    `71_Simplify_Path`().simplifyPath("/home/user/Documents/../Pictures").also { println(it) } // "/home/user/Pictures"
    `71_Simplify_Path`().simplifyPath("/../").also { println(it) } // "/"
    `71_Simplify_Path`().simplifyPath("/.../a/../b/c/../d/./").also { println(it) } // "/.../b/d"
}
