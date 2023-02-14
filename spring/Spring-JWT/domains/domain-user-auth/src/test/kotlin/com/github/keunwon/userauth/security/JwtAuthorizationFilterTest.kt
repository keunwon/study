package com.github.keunwon.userauth.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.keunwon.userauth.globalExpiredToken
import com.github.keunwon.userauth.globalLoginToken
import com.github.keunwon.userauth.jwtProviderFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

class JwtAuthorizationFilterTest {
    private val jwtProvider = jwtProviderFixture
    private val objectMapper = jacksonObjectMapper()

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(HomeTestController())
            .addFilter<StandaloneMockMvcBuilder>(JwtAuthorizationFilter(jwtProvider, objectMapper))
            .build()
    }

    @Test
    fun `Authorization 헤더에 Bearer 형식으로 사용하면 토큰 검증에 성공(200)`() {
        val accessToken = globalLoginToken.accessToken.value

        mockMvc.get("/") {
            headers {
                setBearerAuth(accessToken)
            }
        }.andExpect {
            status { isOk() }
        }
    }

    @Test
    fun `Authorization 헤더 값이 Bearer 시작하지 않으면 403 응답`() {
        val accessToken = globalLoginToken.accessToken.value

        mockMvc.get("/") {
            headers {
                set(HttpHeaders.AUTHORIZATION, accessToken)
            }
        }.andExpect {
            status { isForbidden() }
        }
    }

    @Test
    fun `Authorization 헤더 값에 토큰을 입력하지 않으면 403 응답`() {
        mockMvc.get("/") {
            headers {
                setBearerAuth("")
            }
        }.andExpect {
            status { isForbidden() }
        }
    }

    @Test
    fun `Authorization 헤더 값에 만료된 토큰을 사용하면 403 응답`() {
        val expiredToken = globalExpiredToken

        mockMvc.get("/") {
            headers {
                setBearerAuth(expiredToken)
            }
        }.andExpect {
            status { isForbidden() }
        }
    }
}
