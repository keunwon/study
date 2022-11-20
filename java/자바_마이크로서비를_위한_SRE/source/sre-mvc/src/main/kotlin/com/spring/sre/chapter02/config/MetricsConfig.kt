package com.spring.sre.chapter02.config

import com.spring.sre.chapter02.filter.Vehicle
import com.spring.sre.chapter02.filter.VehicleMeterBinder
import io.micrometer.cloudwatch2.CloudWatchMeterRegistry
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.config.MeterFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MetricsConfig {

    //@Bean
    fun commonTags(
        @Value("\${spring.application.name}") appName: String,
        @Value("\${spring.profiles.active}") profile: String,
    ): MeterFilter {
        return MeterFilter.commonTags(
            listOf(
                Tag.of("application", appName),
                Tag.of("region", "seoul"),
                Tag.of("stack", profile)
            )
        )
    }

    @Bean
    fun vehicleListener(vehicleMeterBinder: VehicleMeterBinder): VehicleMeterBinder.VehicleListener {
        return vehicleMeterBinder.VehicleListener()
    }

    @Bean
    fun vehicleMeterBinder(): VehicleMeterBinder {
        return VehicleMeterBinder(Vehicle(this, 0))
    }

    //@Bean
    fun cloudwatchCustomizations(): MeterRegistryCustomizer<CloudWatchMeterRegistry> {
        return MeterRegistryCustomizer {
            it.config()
                .meterFilter(MeterFilter.acceptNameStartsWith("prometheus"))
                .meterFilter(MeterFilter.deny())
        }
    }
}
