package com.keunwon.algorithm.programmers

/**
 * Title: 핸드폰 번호 가리기
 * Level: 1
 **/
class Lessons12948 {
    fun solution(phone_number: String): String {
        return "*".repeat(phone_number.length - 4) + phone_number.takeLast(4)
    }
}
