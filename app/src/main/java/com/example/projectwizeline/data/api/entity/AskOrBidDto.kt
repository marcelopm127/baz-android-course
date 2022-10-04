package com.example.projectwizeline.data.api.entity

import com.example.projectwizeline.domain.entity.AskOrBid
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AskOrBidDto(
    @SerializedName("book")
    @Expose
    val idBook: String? = "",

    @SerializedName("price")
    @Expose
    val price: String? = "",

    @SerializedName("amount")
    @Expose
    val amount: String? = "",
) {
    fun toAskOrBid(type: String): AskOrBid {
        return AskOrBid(
            idBook = idBook,
            price = price?.toDouble(),
            amount = amount?.toDouble(),
            type = type
        )
    }
}