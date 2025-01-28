package com.keunwon.algorithm.kakao

import feign.RequestTemplate

fun RequestTemplate.enableApplicationJsonForContentType() = apply {
    header("Content-Type", "application/json")
}

fun RequestTemplate.enableAuthorization(token: String) = apply {
    header("Authorization", token)
}

fun RequestTemplate.enableXAuthToken(token: String) = apply {
    header("X-Auth-Token", token)
}
