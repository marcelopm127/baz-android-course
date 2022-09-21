package com.example.projectwizeline.data.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AskOrBid(
    @SerializedName("book")
    @Expose
    val idBook: String? = "",

    @SerializedName("price")
    @Expose
    val price: String? = "",

    @SerializedName("amount")
    @Expose
    val amount: String? = "",
)