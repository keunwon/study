package com.keunwon.algorithm.againresolve

class ALessons64065 {
    fun solution(s: String): IntArray {
        val arr = s.substring(2, s.length - 2)
            .split("},{")
            .sortedBy { it.length }
        val set = mutableSetOf<Int>()
        for (str in arr) {
            for (num in str.split(",").map(String::toInt)) {
                if (!set.contains(num)) set.add(num)
            }
        }
        return set.toIntArray()
    }
}

fun main() {
    arrayOf(
        "{{2},{2,1},{2,1,3},{2,1,3,4}}",
        "{{1,2,3},{2,1},{1,2,4,3},{2}}",
        "{{20,111},{111}}",
        "{{123}}",
        "{{4,2,3},{3},{2,3,4,1},{2,3}}",
    ).forEach { answer ->
        ALessons64065().solution(answer).also { println(it.contentToString()) }
    }
}
