package com.keunwon.algorithm.againresolve

class ALessons72410 {
    fun solution(new_id: String): String {
        return new_id
            .lowercase()
            .replace("""[^\w\d-_.]""".toRegex(), "")
            .replace("""[.]{2,}""".toRegex(), ".")
            .replace("^[.]|[.]$".toRegex(), "")
            .let { it.ifBlank { "a" } }
            .take(15)
            .replace("[.]$".toRegex(), "")
            .let { it + if (it.length >= 3) "" else "${it.last()}".repeat(3 - it.length) }
    }
}

fun main() {
    arrayOf(
        "...!@BaT#*..y.abcdefghijklm",
        "z-+.^.",
        "=.=",
        "123_.def",
        "abcdefghijklmn.p"
    ).forEach { id -> ALessons72410().solution(id).also { println(it) } }
}
