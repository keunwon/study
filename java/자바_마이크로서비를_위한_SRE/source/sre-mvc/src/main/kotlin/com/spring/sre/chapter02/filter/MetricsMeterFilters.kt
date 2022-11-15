package com.spring.sre.chapter02.filter

import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.config.MeterFilter
import io.micrometer.core.instrument.config.MeterFilterReply

class TestMeterFilter : MeterFilter {

    override fun accept(id: Meter.Id): MeterFilterReply {
        if (id.name == "test") {
            return MeterFilterReply.DENY
        }
        return MeterFilterReply.NEUTRAL
    }

    fun config() {
        Metrics.globalRegistry.config()
            .meterFilter(MeterFilter.ignoreTags("too.much.information"))
            .meterFilter(MeterFilter.denyNameStartsWith("jvm"))
    }
}

