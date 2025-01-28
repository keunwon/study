package com.keunwon.algorithm.programmers

import java.util.*

class Lesson60058 {
    fun solution(p: String): String = dfs(p)

    private fun dfs(p: String): String {
        if (p.isBlank()) return ""

        var left = 0
        var right = 0
        var u = ""
        var v = ""

        for ((i, c) in p.withIndex()) {
            u += c
            if (c == '(') ++left else ++right
            if (left == right) {
                v = p.substring(i + 1)
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
        u = u.substring(1, u.lastIndex).map { if (it == '(') ')' else '(' }.joinToString("")
        return tmp + u
    }

    private fun check(str: String): Boolean {
        val stack = Stack<Char>()
        for (c in str) {
            if (c == '(') {
                stack.push(c)
                continue
            }

            if (stack.isEmpty()) return false
            stack.pop()
        }
        return stack.isEmpty()
    }
}
