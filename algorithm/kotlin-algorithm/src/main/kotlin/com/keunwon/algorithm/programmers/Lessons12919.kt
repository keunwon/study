package com.keunwon.algorithm.programmers

/**
 * Title: 서울에서 김서방 찾기
 * Level: 1
 **/
class Lessons12919 {
    fun solution(seoul: Array<String>): String {
        val findIndex = seoul.indexOf("Kim")
        return "김서방은 ${findIndex}에 있다"
    }
}
