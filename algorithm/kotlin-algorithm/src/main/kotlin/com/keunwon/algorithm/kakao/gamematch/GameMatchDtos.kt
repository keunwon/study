package com.keunwon.algorithm.kakao.gamematch

import com.fasterxml.jackson.annotation.JsonProperty

data class WaitingLineResponse(
    @JsonProperty("waiting_line")
    val elements: List<Element> = mutableListOf(),
) {
    data class Element(val id: Int, val from: String)
}

data class GameResult(
    @JsonProperty("game_result")
    val elements: List<Element> = mutableListOf(),
) {
    data class Element(val win: Int, val lose: Int, val taken: Int)
}

data class UserInfo(
    @JsonProperty("user_info")
    val elements: List<Element> = mutableListOf(),
) {
    data class Element(val id: Int, val grade: Int)
}

data class MatchRequest(
    val pairs: List<Pair<Int, Int>> = mutableListOf(),
)

data class MatchResponse(
    val status: String,
    val time: Int,
)

data class ChangeGradeRequest(
    val commands: List<Element> = mutableListOf(),
) {
    data class Element(val id: Int, val grade: Int)
}

data class ChangeGradeResponse(val status: String)

data class ScoreResponse(
    val status: String,
    val efficiencyScore: Double,
    val accuracyScore1: Float,
    val accuracyScore2: Float,
    val score: Float,
)
