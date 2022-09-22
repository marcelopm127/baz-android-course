package com.example.projectwizeline.data.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PayloadDetailDto(
    @SerializedName("book")
    @Expose
    val idBook: String? = "",

    @SerializedName("volume")
    @Expose
    val volume: String? = "",

    @SerializedName("high")
    @Expose
    val high: String? = "",

    @SerializedName("last")
    @Expose
    val last: String? = "",

    @SerializedName("low")
    @Expose
    val low: String? = "",

    @SerializedName("vwap")
    @Expose
    val vwap: String? = "",

    @SerializedName("ask")
    @Expose
    val ask: String? = "",

    @SerializedName("bid")
    @Expose
    val bid: String? = "",

    @SerializedName("created_at")
    @Expose
    val createdAt: String? = ""
)