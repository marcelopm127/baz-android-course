package com.example.projectwizeline.data.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PayloadDto(
    @SerializedName("book")
    @Expose
    val idBook: String? = "",

    @SerializedName("minimum_price")
    @Expose
    val minimumPrice: String? = "",

    @SerializedName("maximum_price")
    @Expose
    val maximumPrice: String? = ""
)