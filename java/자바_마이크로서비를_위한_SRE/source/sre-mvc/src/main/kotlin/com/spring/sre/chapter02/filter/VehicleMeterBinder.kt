package com.spring.sre.chapter02.filter

import io.micrometer.core.instrument.FunctionCounter
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder
import org.apache.logging.log4j.LogManager
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import java.util.concurrent.atomic.AtomicLong

class VehicleMeterBinder(
    private val vehicle: Vehicle,
) : MeterBinder {

    override fun bindTo(registry: MeterRegistry) {
        Gauge.builder("vehicle.speed", vehicle) { it.speed.get().toDouble() }
            .baseUnit("km/h")
            .description("Current vehicle speed")
            .register(registry)

        FunctionCounter.builder("vehicle.odometer", vehicle) { it.odometer.toDouble() }
            .baseUnit("kilometers")
            .description("The amount of distance this vehicle has traveled")
            .register(registry)
    }

    inner class VehicleListener : ApplicationListener<Vehicle> {
        private val log = LogManager.getLogger(VehicleListener::class)

        override fun onApplicationEvent(event: Vehicle) {
            vehicle.change(event)
            log.info("> vehicle-listener: $vehicle")
        }
    }
}

class Vehicle(
    source: Any,
    val speed: AtomicLong,
    val odometer: AtomicLong = AtomicLong(speed.get()),
) : ApplicationEvent(source) {

    constructor(source: Any, speed: Long) : this(source, AtomicLong(speed))

    fun change(vehicle: Vehicle) {
        speed.updateAndGet { vehicle.speed.get() }
        odometer.updateAndGet { it + vehicle.speed.get() }
    }

    override fun toString(): String {
        return "Vehicle(speed=$speed, odometer=$odometer)"
    }
}
