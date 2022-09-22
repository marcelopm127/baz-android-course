package com.example.projectwizeline.data.api.response

import com.example.projectwizeline.data.api.entity.PayloadDetailDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TickerResponse(
    @SerializedName("payload")
    @Expose
    val payloadDetailDto: PayloadDetailDto?
): BaseResponse()