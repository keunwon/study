package com.spring.sre

import io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import reactor.blockhound.BlockHound

@ConfigurationPropertiesScan
@SpringBootApplication
class SpringWebfluxApplication {

    @Profile("local")
    @Bean
    fun init(): CommandLineRunner {
        return CommandLineRunner {
            BlockHound.install({
                with (it) {
                    allowBlockingCallsInside(MacOSDnsServerAddressStreamProvider::class.qualifiedName, "loadNativeLibrary")
                    /*allowBlockingCallsInside(RollingFileAppender::class.qualifiedName, "append")
                    allowBlockingCallsInside(Logger::class.qualifiedName, "log")*/
                }
            })
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringWebfluxApplication>(*args)
    /*
    val counter = Metrics.counter("load.test.requests")
    val client = WebClient.builder()
        .baseUrl("http://192.168.45.42:8080")
        .build()

    Flux
        .generate<Long, AtomicLong>({ AtomicLong() }, { atomic, sink ->
            val i = atomic.getAndIncrement()
            sink.next(i)
            atomic
        })
        .limitRate(1)
        .flatMap {
            client.get().uri("/chapter/2/test/requests")
                .exchangeToMono { response ->
                    if (response.statusCode().is2xxSuccessful) {
                        counter.increment()
                    }
                    response.bodyToMono(String::class.java)
                }
        }
        .blockLast()
    */
}
