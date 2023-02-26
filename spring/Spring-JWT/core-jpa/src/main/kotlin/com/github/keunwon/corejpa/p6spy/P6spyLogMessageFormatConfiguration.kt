package com.github.keunwon.corejpa.p6spy

import com.p6spy.engine.spy.P6SpyOptions
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class P6spyLogMessageFormatConfiguration {
    @PostConstruct
    fun post() {
        P6SpyOptions.getActiveInstance().logMessageFormat = PrettySqlFormattingStrategy::class.java.name
    }
}
