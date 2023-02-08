package com.keunwon.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.common.ControllerErrorHandler
import com.keunwon.jwt.common.ErrorDto
import com.keunwon.jwt.domain.USER_EMAIL
import com.keunwon.jwt.security.jwt.AuthorizationHeader
import com.keunwon.jwt.security.jwt.ClaimsInfo
import com.keunwon.jwt.security.jwt.LoginUserDto
import com.keunwon.jwt.security.jwt.LoginUserResolver
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import java.time.format.DateTimeFormatter
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

abstract class RestDocsSupport {
    fun createMockMvc(
        controller: Any,
        restDocumentation: RestDocumentationContextProvider,
        filters: List<Filter> = emptyList(),
    ): MockMvc {
        return MockMvcBuilders.standaloneSetup(controller)
            .apply<StandaloneMockMvcBuilder>(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
            )
            .addFilters<StandaloneMockMvcBuilder>(*filters.toTypedArray())
            .setCustomArgumentResolvers(*getCustomResolvers())
            .setControllerAdvice(ControllerErrorHandler())
            .build()
    }

    fun createAuthMockMvc(
        controller: Any,
        resetDocumentationContextProvider: RestDocumentationContextProvider,
        claimsInfo: ClaimsInfo = createClaimsInfo(userId = 1L),
        filters: MutableList<Filter> = mutableListOf(),
    ): MockMvc {
        filters.add(JwtAuthorizationFilterStub(claimsInfo, testObjectMapper))
        return createMockMvc(controller, resetDocumentationContextProvider, filters)
    }

    companion object {
        private fun createDefaultLoginUserDto(): LoginUserDto {
            return LoginUserDto(
                id = 1L,
                email = USER_EMAIL,
            )
        }

        private fun getCustomResolvers(): Array<HandlerMethodArgumentResolver> {
            return listOf(LoginUserResolver()).toTypedArray()
        }
    }
}

const val VALID_TOKEN = "VALID_TOKEN"
const val INVALID_TOKEN = "INVALID_TOKEN"

private class JwtAuthorizationFilterStub(
    private val claimsInfo: ClaimsInfo,
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            val header = AuthorizationHeader(request)
            require(header.credentials == VALID_TOKEN)
            registerSecurityContext()
            filterChain.doFilter(request, response)
        }.onFailure {
            response.writeErrorDto("토큰이 유효하지 않습니다.")
        }
    }

    private fun registerSecurityContext() {
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken
            .authenticated(claimsInfo, "", claimsInfo.getGrantedAuthorities())
    }

    private fun HttpServletResponse.writeErrorDto(message: String) = apply {
        val errorDto = ErrorDto(HttpStatus.UNAUTHORIZED.value(), message)
        status = HttpStatus.FORBIDDEN.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        objectMapper.writeValue(outputStream, errorDto)
    }
}

val testObjectMapper: ObjectMapper = run {
    val serializer = LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME)
    val javaTimeModule = JavaTimeModule().apply { addSerializer(serializer) }
    jacksonObjectMapper().registerModule(javaTimeModule)
}

fun <T> toJson(value: T): String = testObjectMapper.writeValueAsString(value)
