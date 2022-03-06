package com.example.reactive.infrastucture.repository.db

import org.springframework.data.annotation.Id

data class Customer(
    @Id
    val id: Integer? = null,
    val name: String
)
