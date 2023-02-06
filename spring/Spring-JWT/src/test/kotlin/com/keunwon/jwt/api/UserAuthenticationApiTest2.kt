package com.keunwon.jwt.api

import com.keunwon.jwt.InmemoryAuthenticationCodeRepository
import com.keunwon.jwt.InmemoryUserPasswordHistoryRepository
import com.keunwon.jwt.InmemoryUserRepository
import com.keunwon.jwt.RestDocsSupport
import com.keunwon.jwt.TestPasswordEncoder
import com.keunwon.jwt.VALID_TOKEN
import com.keunwon.jwt.domain.UserBuilder
import com.keunwon.jwt.domain.user.UserPasswordHistory
import com.keunwon.jwt.makeDocument
import com.keunwon.jwt.param
import com.keunwon.jwt.security.jwt.bearer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpHeaders
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@ExtendWith(RestDocumentationExtension::class)
class UserAuthenticationApiTest2 : RestDocsSupport() {
    private lateinit var mockMvc: MockMvc

    private val userRepository = InmemoryUserRepository()
    private val authenticationRepository = InmemoryAuthenticationCodeRepository()
    private val userPasswordHistoryRepository = InmemoryUserPasswordHistoryRepository()
    private val passwordEncoder = TestPasswordEncoder

    private val userAuthenticationService = UserAuthenticationService(
        userRepository,
        authenticationRepository,
        userPasswordHistoryRepository,
        passwordEncoder,
    )

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        mockMvc = createAuthMockMvc(UserAuthenticationApi(userAuthenticationService), restDocumentation)
    }

    @Test
    fun `사용자 비밀번호를 변경합니다`() {
        val newPassword = "NEW_PASSWORD"
        val user = UserBuilder(id = 1L).build().also {
            userRepository.save(it)
            userPasswordHistoryRepository.save(UserPasswordHistory(it))
        }

        mockMvc.post("/auth/me/password") {
            bearer(VALID_TOKEN)
            param("password", newPassword)
        }.andExpect {
            status { isNoContent() }

            assertAll({
                assertTrue(user.matchPassword(newPassword, passwordEncoder))
                assertThat(userPasswordHistoryRepository.findAllByUserId(user.id)).hasSize(2)
            })
        }.andDo {
            makeDocument("사용자 비밀번호 변경") {
                requestHeaders(
                    HttpHeaders.AUTHORIZATION to "accessToken",
                )
                requestParams(
                    "password" param "변경할 비밀번호",
                )
            }
        }
    }
}
