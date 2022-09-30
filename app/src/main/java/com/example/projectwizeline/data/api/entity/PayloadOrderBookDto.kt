package com.example.projectwizeline.data.api.entity

import com.example.projectwizeline.domain.constant.Constants
import com.example.projectwizeline.domain.entity.PayloadOrderBook
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PayloadOrderBookDto(
    @SerializedName("asks")
    @Expose
    val asks: List<AskOrBidDto>? = emptyList(),

    @SerializedName("bids")
    @Expose
    val bids: List<AskOrBidDto>? = emptyList()
) {
    fun toPayloadOrderBook(): PayloadOrderBook {
        return PayloadOrderBook(
            asks = asks?.map {
                it.toAskOrBid(Constants.ASK)
            }?.toMutableList() ?: mutableListOf(),
            bids = bids?.map {
                it.toAskOrBid(Constants.BID)
            }?.toMutableList() ?: mutableListOf()
        )
    }
}