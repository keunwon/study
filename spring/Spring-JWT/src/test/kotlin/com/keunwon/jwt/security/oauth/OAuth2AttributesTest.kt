package com.keunwon.jwt.security.oauth

import org.junit.jupiter.api.Test

internal class OAuth2AttributesTest {

    @Test
    fun `Naver OAuth`() {
        val map = linkedMapOf(
            "name" to "홍길동",
            "nickname" to "nickname",
            "email" to "test@test.com",
            "id" to "1111111111",
            "gender" to "M",
            "birthday" to "11-11",
            "birthyear" to "1000"
        )
        val attributes = mapOf("response" to map)

        val oAuth2Attributes = NaverOAuth2Attributes(attributes, "response")
        println(oAuth2Attributes.gender)
    }

    @Test
    fun `Google`() {
        val map = linkedMapOf(
            "name" to "홍길동",
            "email" to "test@test",
            "sub" to "12345656778651151",
            "picture" to "https://test.com",
            "locale" to "ko",
        )

        val oAuth2Attributes = GoogleOAuth2Attributes(map, "sub")
    }
}
