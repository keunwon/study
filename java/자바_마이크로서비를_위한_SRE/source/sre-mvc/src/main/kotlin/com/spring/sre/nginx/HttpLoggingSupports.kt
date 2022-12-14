package com.spring.sre.nginx

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.net.URLDecoder
import java.nio.charset.Charset
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter("/**")
@Order(1)
@Component
class HttpLoggingFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val requestCache = ContentCachingRequestWrapper(request)
        val responseCache = ContentCachingResponseWrapper(response)

        log.info(createRequestMessage(requestCache))
        filterChain.doFilter(requestCache, responseCache)
        log.info(createResponseMessage(requestCache, responseCache))

        responseCache.copyBodyToResponse()
    }

    private fun createRequestMessage(request: ContentCachingRequestWrapper): String {
        return StringBuilder().apply {
            append("> Request ")
            append("Method: ").append(request.method)
            append(", URL: ").append(request.servletPath)
            appendQueryString(", Query-String: ", request.queryString, request.characterEncoding)
            if (log.isDebugEnabled) appendRequestHeadersAsString(", Headers: ", request)
        }.toString()
    }

    private fun createResponseMessage(
        request: ContentCachingRequestWrapper,
        response: ContentCachingResponseWrapper,
    ): String {
        return StringBuilder().apply {
            append("> Response ")
            append("Status: ").append(response.status)
            append(", URL: ").append(request.servletPath)
            appendQueryString(", Query-String: ", request.queryString, request.characterEncoding)
            appendContentAsByteArray(", Req-Body: ", request.contentAsByteArray, request.characterEncoding)
            if (log.isDebugEnabled) appendResponseHeadersAsString(", Res-Headers: ", response)
            appendContentAsByteArray(", Res-Body: ", response.contentAsByteArray, request.characterEncoding)
        }.toString()
    }

    private fun StringBuilder.appendResponseHeadersAsString(prefix: String, response: HttpServletResponse) = apply {
        val message = response.headerNames.joinToString { name -> "$name: ${response.getHeader(name)}" }
        append(prefix).append(message)
    }

    private fun StringBuilder.appendRequestHeadersAsString(prefix: String, request: HttpServletRequest) = apply {
        val list = mutableListOf<String>()
        for (name in request.headerNames) {
            list.add("$name: ${request.getHeader(name)}")
        }
        append(prefix).append(list.joinToString())
    }

    private fun StringBuilder.appendQueryString(prefix: String, query: String?, encoding: String) = apply {
        if (query == null || query.isBlank()) return@apply
        append(prefix).append(URLDecoder.decode(query, Charset.forName(encoding)))
    }

    private fun StringBuilder.appendContentAsByteArray(
        prefix: String,
        content: ByteArray,
        encoding: String,
        maxLength: Int = 150,
    ) = apply {
        if (content.isEmpty()) return@apply

        if (content.size <= maxLength) {
            append(prefix).append(jacksonObjectMapper().readTree(content).toString())
        } else {
            val message = String(content, 0, maxLength, Charset.forName(encoding))
            append(prefix).append(message.replace("\n", "")).append(" ....")
        }
    }

    companion object : Log4j2Support
}

interface Log4j2Support {
    val log: Logger
        get() = LogManager.getLogger(this::class.java)
}
