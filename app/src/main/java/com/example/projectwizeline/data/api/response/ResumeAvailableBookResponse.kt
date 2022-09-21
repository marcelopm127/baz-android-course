package com.example.projectwizeline.data.api.response

import com.example.projectwizeline.data.api.entity.Payload
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResumeAvailableBookResponse(
    @SerializedName("payload")
    @Expose
    val payload: List<Payload>? = emptyList()
): BaseResponse()