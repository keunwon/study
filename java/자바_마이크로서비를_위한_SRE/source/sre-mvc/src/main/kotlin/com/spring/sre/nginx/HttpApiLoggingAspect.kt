package com.spring.sre.nginx

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Aspect
@Component
class HttpApiLoggingAspect {

    @Pointcut("execution(public * com.spring.sre..*Controller.*(..))")
    fun controllerPointCut() = run { }

    @Pointcut("execution(public * com.spring.sre..*Api.*(..))")
    fun apiPointCut() = run {}


    @Around("controllerPointCut() || apiPointCut()")
    fun logging(joinPoint: ProceedingJoinPoint): Any {
        val request = getHttpServlet().request.also { loggingRequest(it) }
        return joinPoint.proceed().also { result ->
            val response = getHttpServlet().response ?: return@also
            loggingResponse(request, response, result)
        }
    }

    private fun getHttpServlet(): ServletRequestAttributes {
        return RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
    }

    private fun loggingRequest(request: HttpServletRequest) {
        with(request) {
            val body = jacksonObjectMapper().writeValueAsString(parameterMap)
            log.info("> [Request] $method $servletPath, content-type=$contentType, body=$body")
        }
    }

    private fun loggingResponse(request: HttpServletRequest, response: HttpServletResponse, proceed: Any) {
        with(response) {
            val body = getResponseBodyToString(this, proceed)
            log.info("> [Response] $status ${request.servletPath}, content-type=$contentType, response=$body")
        }
    }

    private fun getResponseBodyToString(response: HttpServletResponse, result: Any): String = when {
        MediaType.APPLICATION_JSON_VALUE == response.contentType && result is ResponseEntity<*> ->
            jacksonObjectMapper().writeValueAsString(result)
        else -> ""
    }

    companion object : Log4j2Support
}
