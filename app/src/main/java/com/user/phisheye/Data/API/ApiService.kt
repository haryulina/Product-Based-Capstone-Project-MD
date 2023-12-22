package com.user.phisheye.Data.API

import com.user.phisheye.Data.Pref.Login
import com.user.phisheye.Data.Pref.PredictPhishingResponse
import com.user.phisheye.Data.Pref.Register
import com.user.phisheye.Data.Pref.Report
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<Login>

    @POST("signup")
    @FormUrlEncoded
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<Register>

    @POST("reportLink")
    @FormUrlEncoded
    fun report(
        @Field("link") name: String
    ): Call<Report>

    @POST("/predict")
    suspend fun predictPhishing(
        @Body requestBody: Map<String, String>
    ): Response<PredictPhishingResponse>
}