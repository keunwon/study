package com.spring.sre.chapter03

import org.springframework.cloud.sleuth.Tracer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chapter/3/customer")
class CustomerApi(
    private val tracer: Tracer
) {

    @GetMapping("/{id}")
    suspend fun findCustomerById(@PathVariable id: String): ResponseEntity<Customer> {
        val span = tracer.nextSpan().name("findCustomer")

        tracer.withSpan(span.start()).use {
            val customer = Customer("address-1", "country-1")
            span.tag("country", customer.county)
            return ResponseEntity.ok(customer)
        }
    }
}

data class Customer(val address: String, val county: String)
