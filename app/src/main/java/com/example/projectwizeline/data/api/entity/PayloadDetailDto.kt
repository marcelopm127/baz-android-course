package com.example.projectwizeline.data.api.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PayloadDetailDto(
    @SerializedName("book")
    @Expose
    val idBook: String? = "",

    @SerializedName("high")
    @Expose
    val high: String? = "",

    @SerializedName("last")
    @Expose
    val last: String? = "",

    @SerializedName("low")
    @Expose
    val low: String? = ""
)