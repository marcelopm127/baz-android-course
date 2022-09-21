package com.example.projectwizeline.data.api.response

import com.example.projectwizeline.data.api.entity.PayloadDetail
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TickerResponse(
    @SerializedName("payload")
    @Expose
    val payloadDetail: PayloadDetail?
): BaseResponse()