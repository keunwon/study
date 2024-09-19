package com.keunwon.algorithm.kakao

import feign.RequestLine

interface AuthApi {
    @RequestLine("POST /start")
    fun fetchAuthKey(request: AuthKeyRequest): AuthKeyResponse
}
