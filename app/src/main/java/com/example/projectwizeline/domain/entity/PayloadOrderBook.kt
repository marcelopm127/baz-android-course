package com.example.projectwizeline.domain.entity

data class PayloadOrderBook(
   val asks: List<AskOrBid>? = emptyList(),

    val bids: List<AskOrBid>? = emptyList(),
)