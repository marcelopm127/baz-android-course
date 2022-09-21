package com.example.projectwizeline.data.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("book")
    @Expose
    val idBook: String? = "",

    @SerializedName("minimum_amount")
    @Expose
    val minimumAmount: String? = "",

    @SerializedName("maximum_amount")
    @Expose
    val maximumAmount: String? = "",

    @SerializedName("minimum_price")
    @Expose
    val minimumPrice: String? = "",

    @SerializedName("maximum_price")
    @Expose
    val maximumPrice: String? = "",

    @SerializedName("minimum_value")
    @Expose
    val minimumValue: String? = "",

    @SerializedName("maximum_value")
    @Expose
    val maximumValue: String? = ""
)