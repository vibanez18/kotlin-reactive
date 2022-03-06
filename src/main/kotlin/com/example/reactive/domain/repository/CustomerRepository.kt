package com.example.reactive.domain.repository

import com.example.reactive.infrastucture.repository.db.Customer
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CustomerRepository: ReactiveCrudRepository<Customer, Int>