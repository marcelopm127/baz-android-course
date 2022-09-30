package com.example.projectwizeline.domain.entity

data class AskOrBid(
    val idBook: String? = "",
    val price: Double? = 0.0,
    val amount: Double? = 0.0,
    val type: String? = ""
)