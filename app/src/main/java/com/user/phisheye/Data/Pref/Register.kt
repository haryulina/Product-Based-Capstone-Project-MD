package com.user.phisheye.Data.Pref

import com.google.gson.annotations.SerializedName

data class Register(
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("user_id")
    val user_id: Int
)
