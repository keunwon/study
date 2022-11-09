package com.spring.sre

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebfluxApplication

fun main(args: Array<String>) {
    runApplication<SpringWebfluxApplication>(*args)
    /*val counter = Metrics.counter("load.test.requests")
    val client = WebClient.builder()
        .baseUrl("http://localhost:8080")
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
        .blockLast()*/
}
