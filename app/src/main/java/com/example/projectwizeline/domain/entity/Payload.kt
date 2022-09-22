package com.example.projectwizeline.domain.entity

data class Payload(
    val idBook: String? = "",
    val minimumPrice: Double? = 0.0,
    val maximumPrice: Double? = 0.0
)