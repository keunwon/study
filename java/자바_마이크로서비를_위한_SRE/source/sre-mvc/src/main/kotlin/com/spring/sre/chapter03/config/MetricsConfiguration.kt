package com.spring.sre.chapter03.config

import com.blueconic.browscap.UserAgentService
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags
import org.springframework.boot.actuate.metrics.web.servlet.DefaultWebMvcTagsProvider
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class MetricsConfiguration {

    @Bean
    fun customizeResMetrics(): WebMvcTagsProvider {
        val userAgentParser = UserAgentService().loadParser()

        return object : DefaultWebMvcTagsProvider() {
            override fun getTags(
                request: HttpServletRequest,
                response: HttpServletResponse?,
                handler: Any?,
                exception: Throwable?
            ): MutableIterable<Tag> {
                val capabilities = userAgentParser.parse(request.getHeader(HttpHeaders.USER_AGENT))
                return Tags.concat(
                    super.getTags(request, response, handler, exception),
                    "browser", capabilities.browser,
                    "browser.version", capabilities.platformVersion,
                )
            }
        }
    }
}
