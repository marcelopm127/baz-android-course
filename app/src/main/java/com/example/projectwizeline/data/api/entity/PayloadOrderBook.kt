package com.example.projectwizeline.data.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PayloadOrderBook(
    @SerializedName("asks")
    @Expose
    val asks: List<AskOrBid>? = emptyList(),

    @SerializedName("bids")
    @Expose
    val bids: List<AskOrBid>? = emptyList(),

    @SerializedName("updated_at")
    @Expose
    val updatedAt: String? = "",

    @SerializedName("sequence")
    @Expose
    val sequence: String? = ""
)