package com.example.projectwizeline.data.api.response

import com.example.projectwizeline.data.api.entity.PayloadOrderBookDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderBookResponse(
    @SerializedName("payload")
    @Expose
    val payloadOrderBook: PayloadOrderBookDto?
): BaseResponse()