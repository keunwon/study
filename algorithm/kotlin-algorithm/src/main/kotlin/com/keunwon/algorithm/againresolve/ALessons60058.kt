package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons60058 {
    fun solution(p: String): String {
        return dfs(p)
    }

    private fun dfs(p: String): String {
        if (p.isBlank()) return ""

        var u = ""
        var v = ""
        var lCount = 0
        var rCount = 0

        for (c in p) {
            u += c
            if (c == '(') lCount++ else rCount++
            if (lCount == rCount) {
                v = p.substring(lCount + rCount)
                break
            }
        }

        if (check(u)) {
            u += dfs(v)
            return u
        }

        var tmp = "("
        tmp += dfs(v)
        tmp += ")"
        u = reverse(u.substring(1 until u.lastIndex))
        return tmp + u
    }

    private fun reverse(p: String): String {
        val result = StringBuilder()
        for (c in p) {
            result.append(if (c == '(') ')' else '(')
        }
        return result.toString()
    }

    private fun check(p: String): Boolean {
        val stack = Stack<Char>()

        for (c in p) {
            if (c == '(') stack.push(c)
            else if (stack.isEmpty() && c == ')') return false
            else if (stack.isNotEmpty() && c == ')') stack.pop()
        }
        return stack.isEmpty()
    }
}

fun main() {
    arrayOf(
        "(()())()",
        ")(",
        "()))((()"
    ).forEach { ALessons60058().solution(it).also { r -> println(r) } }
}
