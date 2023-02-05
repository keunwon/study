package com.keunwon.jwt

import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.security.jwt.AuthorizationHeader
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

const val VALID_TOKEN = "valid_token"
const val INVALID_TOKEN = "invalid_token"

object JwtAuthorizationFilterStub : OncePerRequestFilter() {
    private val objectMapper = testObjectMapper

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            AuthorizationHeader(request).also {
                require(it.credentials == VALID_TOKEN)
            }
            filterChain.doFilter(request, response)
        }.onFailure {
            response.writeErrorDto("토큰이 유효하지 않습니다.")
        }
    }

    private fun HttpServletResponse.writeErrorDto(message: String) {
        val errorDto = ErrorDto(HttpStatus.UNAUTHORIZED.value(), message)
        objectMapper.writeValue(outputStream, errorDto)
    }
}
