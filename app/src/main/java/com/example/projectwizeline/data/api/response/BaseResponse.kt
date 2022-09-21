package com.example.projectwizeline.data.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("success")
    @Expose
    var success : Boolean? = false
}