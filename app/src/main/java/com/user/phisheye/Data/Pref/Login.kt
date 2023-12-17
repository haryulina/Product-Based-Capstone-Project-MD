package com.user.phisheye.Data.Pref

import com.google.gson.annotations.SerializedName

data class Login(

    @field:SerializedName("loginresult")
    val loginResult: User,
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("access_token")
    val accessToken: String,
    @field:SerializedName("refresh_token")
    val refreshToken: String,
)
