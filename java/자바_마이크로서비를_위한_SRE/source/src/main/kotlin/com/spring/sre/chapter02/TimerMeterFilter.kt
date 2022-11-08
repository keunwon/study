package com.spring.sre.chapter02

import io.micrometer.core.instrument.Meter
import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import io.micrometer.core.instrument.config.MeterFilter
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig

class TimerMeterFilter {

    // 빌더를 이용하여 백분위수 추가
    private val timer = Timer.builder("requests")
        .publishPercentiles(0.99, 0.999)
        .register(Metrics.globalRegistry)

    // MeterFilter 이용하여 백분위수를 추가
    fun meterFilter() {
        Metrics.globalRegistry.config().meterFilter(object : MeterFilter {
            override fun configure(id: Meter.Id, config: DistributionStatisticConfig): DistributionStatisticConfig? {
                if (id.name == "requests") {
                    DistributionStatisticConfig.builder()
                        .percentiles(0.99, 0.999)
                        .build()
                        .merge(config)
                }
                return config
            }
        })
    }
}
