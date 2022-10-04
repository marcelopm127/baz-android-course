package com.example.projectwizeline.data.api.response

import com.example.projectwizeline.data.api.entity.PayloadDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResumeAvailableBookResponse(
    @SerializedName("payload")
    @Expose
    val payloadDto: List<PayloadDto>? = emptyList()
): BaseResponse()