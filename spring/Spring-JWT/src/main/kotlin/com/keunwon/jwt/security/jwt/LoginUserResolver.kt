package com.keunwon.jwt.security.jwt

import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class LoginUser

@Component
class LoginUserResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): LoginUserDto {
        assert(SecurityContextHolder.getContext().authentication.principal is ClaimsInfo)
        val claimsInfo = SecurityContextHolder.getContext().authentication.principal as ClaimsInfo
        return LoginUserDto(claimsInfo)
    }
}

data class LoginUserDto(
    val id: Long,
    val email: String,
) {
    constructor(claimsInfo: ClaimsInfo) : this(
        claimsInfo.userId,
        claimsInfo.subject,
    )
}
