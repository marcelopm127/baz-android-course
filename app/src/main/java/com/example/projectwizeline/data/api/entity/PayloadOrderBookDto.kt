package com.example.projectwizeline.data.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PayloadOrderBookDto(
    @SerializedName("asks")
    @Expose
    val asks: List<AskOrBidDto>? = emptyList(),

    @SerializedName("bids")
    @Expose
    val bids: List<AskOrBidDto>? = emptyList(),

    @SerializedName("updated_at")
    @Expose
    val updatedAt: String? = "",

    @SerializedName("sequence")
    @Expose
    val sequence: String? = ""
)