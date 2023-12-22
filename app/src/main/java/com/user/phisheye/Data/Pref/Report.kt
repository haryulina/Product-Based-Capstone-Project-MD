package com.user.phisheye.Data.Pref

import com.google.gson.annotations.SerializedName

data class Report(
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String
)
