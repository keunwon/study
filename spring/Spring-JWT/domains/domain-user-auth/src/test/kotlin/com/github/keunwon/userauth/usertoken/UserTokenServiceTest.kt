package com.github.keunwon.userauth.usertoken

import com.github.keunwon.user.InmemoryUserRepository
import com.github.keunwon.user.USER_EMAIL
import com.github.keunwon.user.UserBuilder
import com.github.keunwon.userauth.InmemoryUserTokenRepository
import com.github.keunwon.userauth.globalExpiredToken
import com.github.keunwon.userauth.jwtProviderFixture
import io.jsonwebtoken.ExpiredJwtException
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.string.shouldNotBeBlank

@OptIn(ExperimentalKotest::class)
class UserTokenServiceTest : BehaviorSpec({
    val jwtProvider = jwtProviderFixture
    val userRepository = InmemoryUserRepository()
    val userTokenRepository = InmemoryUserTokenRepository()

    val userTokenService = UserTokenService(jwtProvider, userRepository, userTokenRepository)

    Given("로그인 토큰을 한번도 발급받지 않은 사용자가") {
        val user = UserBuilder().build()
        val email = user.profile.email
        userRepository.save(user)

        When("로그인 토큰을 생성하면") {
            val loginToken = userTokenService.issueLoginToken(email)

            Then("accessToken, refreshToken 반환한다") {
                assertSoftly {
                    loginToken.accessToken.value.shouldNotBeBlank()
                    loginToken.refreshToken.value.shouldNotBeBlank()

                    userTokenRepository.findAll().shouldHaveSize(1)
                }
            }
        }

        When("refreshToken 으로 accessToken 재발급을 하면") {
            val reissue = Reissue(user.profile.email, "")

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    userTokenService.reissueAccessToken(reissue)
                }
            }
        }
    }

    Given("이전에 로그인 이력이 있는 사용자가") {
        val user = UserBuilder().build().also {
            userRepository.save(it)
        }
        val email = user.profile.email

        When("로그인 토큰을 발급받으면") {
            val userToken = userTokenService.issueLoginToken(email)

            Then("refreshToken 값을 업데이트하고 accessToken, refreshToken 반환한다") {
                assertSoftly {
                    userToken.accessToken.value.shouldNotBeBlank()
                    userToken.refreshToken.value.shouldNotBeBlank()
                    userTokenRepository.findAll() shouldHaveSize 1
                }
            }
        }

        When("발급 받은 refreshToken 으로 accessToken 재발급 하면") {
            val userToken = userTokenService.issueLoginToken(email)
            val reissue = Reissue(email, userToken.refreshToken.value)

            Then("정상적으로 재발급한다") {
                val accessToken = userTokenService.reissueAccessToken(reissue).value
                accessToken.shouldNotBeBlank()
            }
        }

        When("다른 refreshToken 으로 토큰 재발급 하면") {
            val reissue = Reissue(email, "")

            Then("오류가 발생한다") {
                shouldThrowExactly<IllegalArgumentException> {
                    userTokenService.reissueAccessToken(reissue)
                }
            }
        }
    }

    Given("만료된 refreshToken 으로 accessToken 재발급 하면") {
        val refreshToken = globalExpiredToken
        val reissue = Reissue(USER_EMAIL, refreshToken)

        Then("오류가 발생한다") {
            shouldThrowExactly<ExpiredJwtException> {
                userTokenService.reissueAccessToken(reissue)
            }
        }
    }
})
