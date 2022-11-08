package com.spring.sre.chapter02

import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.MultiGauge
import io.micrometer.core.instrument.Tags
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom

@Component
class GaugeComponent {
    private val log = LogManager.getLogger(GaugeComponent::class)

    private val list = mutableListOf("a", "b", "c", "d", "e", "f", "g")
    private val listGauge = Gauge
        .builder("listGauge", list) { it.size.toDouble() }
        .register(Metrics.globalRegistry)

    private val statues = MultiGauge.builder("customers")
        .tag("country", "US")
        .description("The number of customers by city")
        .baseUnit("customers")
        .register(Metrics.globalRegistry)

    fun incrementGauge() {
        list.add("")
        log.info("gauge - list size: ${listGauge.value()}")
    }

    fun decline() {
        list.removeAt(0)
        log.info("gauge - list size: ${listGauge.value()}")
    }

    fun findMultiGauge(): List<CityDto> {
        val randomNum = ThreadLocalRandom.current().nextInt(1, 5)
        val cityList = List(randomNum) { index -> CityDto(index + 1) }

        statues.register(cityList.map { (cityName, count) ->
            MultiGauge.Row.of(Tags.of("city", cityName), count)
        })
        return cityList
    }
}

data class CityDto (
    val cityName: String,
    val count: Int
) {
    constructor(index: Int) : this("city-$index", index)
}
