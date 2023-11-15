package com.keunwon.algorithm.againresolve

import java.util.*

class ALessons60058 {
    fun solution(p: String): String = dfs(p)

    private fun dfs(w: String): String {
        if (w.isBlank()) return ""

        var (u, v) = "" to ""
        var (lcount, rcount) = 0 to 0

        for ((i, c) in w.withIndex()) {
            if (c == '(') ++lcount else ++rcount
            u += c
            if (lcount == rcount) {
                v = w.substring(i + 1)
                break
            }
        }

        if (check(u)) {
            u += dfs(v)
            return u
        }

        var result = "("
        result += dfs(v)
        result += ")"

        u = u.substring(1, u.lastIndex)
        u.forEach { c -> result += if (c == '(') ')' else '(' }
        return result
    }

    private fun check(word: String): Boolean {
        val stack = Stack<Char>()
        for (c in word) {
            if (c == '(') stack.push(c)
            else {
                if (stack.isEmpty()) return false
                stack.pop()
            }
        }
        return stack.isEmpty()
    }
}

fun main() {
    arrayOf(
        "(()())()",
        ")(",
        "()))((()",
    ).forEach { ALessons60058().solution(it).also(::println) }
}
