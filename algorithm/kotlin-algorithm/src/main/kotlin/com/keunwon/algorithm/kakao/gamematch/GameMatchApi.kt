package com.keunwon.algorithm.kakao.gamematch

import feign.RequestLine

interface GameMatchApi {
    @RequestLine("GET /waiting_line")
    fun fetchWaitingLine(): WaitingLineResponse

    @RequestLine("GET /game_result")
    fun fetchGameResult(): GameResult

    @RequestLine("GET /user_info")
    fun fetchUserInfo(): UserInfo

    @RequestLine("PUT /match")
    fun fetchMatch(request: MatchRequest): MatchResponse

    @RequestLine("PUT /change_grade")
    fun fetchChangeGrade(request: ChangeGradeRequest): ChangeGradeResponse

    @RequestLine("GET /score")
    fun fetchScore(): ScoreResponse
}
