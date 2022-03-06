package com.example.reactive.gateway.controller

import com.example.reactive.domain.repository.CustomerRepository
import com.example.reactive.infrastucture.repository.db.Customer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
class CustomerController(private val customerRepository: CustomerRepository) {

    @GetMapping("/customers")
    fun get(): Flux<Customer> = customerRepository.findAll()
}