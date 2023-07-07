package com.keunwon.algorithm.programmers

/**
 * Title: 대충 만든 자판
 * Level: 1
 **/
class Lessons160586 {
    fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
        val alphabets = mutableMapOf<Char, Int>()
        for (key in keymap) {
            for ((i, w) in key.withIndex()) {
                val n = i + 1
                alphabets[w] = alphabets.getOrDefault(w, n).coerceAtMost(n)
            }
        }
        return targets.map { t ->
            if (t.all { alphabets.contains(it) }) t.sumOf { alphabets.getValue(it) }
            else -1
        }.toIntArray()
    }
}
