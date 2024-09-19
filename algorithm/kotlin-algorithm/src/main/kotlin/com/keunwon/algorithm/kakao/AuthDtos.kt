package com.keunwon.algorithm.kakao

data class AuthKeyRequest(val problem: Int)

data class AuthKeyResponse(
    val authKey: String,
    val problem: Int,
    val time: Int,
)
