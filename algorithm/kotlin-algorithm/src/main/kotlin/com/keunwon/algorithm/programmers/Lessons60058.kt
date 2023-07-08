package com.keunwon.algorithm.programmers

import java.util.*

/**
 * Title: 괄호 변환
 * Level: 2
 **/
class Lessons60058 {
    fun solution(p: String): String = dfs(p)

    private fun dfs(p: String): String {
        if (p.isBlank()) return ""

        var u = ""
        var v = ""
        var lCount = 0
        var rCount = 0

        for ((i, c) in p.withIndex()) {
            if (c == '(') lCount++ else rCount++
            u += c
            if (lCount == rCount) {
                v = p.substring(i + 1)
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

    private fun check(str: String): Boolean {
        val stack = Stack<Char>()
        str.forEach { c ->
            if (c == '(') stack.push(c)
            else {
                if (stack.isEmpty()) return false
                stack.pop()
            }
        }
        return stack.isEmpty()
    }
}
