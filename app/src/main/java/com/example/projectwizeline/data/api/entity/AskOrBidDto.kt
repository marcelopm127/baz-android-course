package com.example.projectwizeline.data.api.entity

import com.example.projectwizeline.domain.entity.AskOrBid
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.lang.Exception

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
        return try {
            AskOrBid(
                idBook = idBook,
                price = price?.toDouble(),
                amount = amount?.toDouble(),
                type = type
            )
        }catch (_:Exception) {
            AskOrBid(
                idBook = idBook,
                price = 0.0,
                amount = 0.0,
                type = type
            )
        }
    }
}