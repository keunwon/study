package com.keunwon.algorithm.programmers

/**
 * Title: 신규 아이디 추천
 * Level: 1
 **/
class Lessons72410 {
    fun solution(new_id: String): String {
        return new_id.lowercase()
            .replace("""[^\w-_.]""".toRegex(), "")
            .replace("[.]{2,}".toRegex(), ".")
            .replace("^[.]|[.]$".toRegex(), "")
            .replace("^$".toRegex(), "a")
            .take(15)
            .replace("[.]$".toRegex(), "")
            .let {
                if (it.length < 3) it + "${it.last()}".repeat(3 - it.length)
                else it
            }
    }
}
