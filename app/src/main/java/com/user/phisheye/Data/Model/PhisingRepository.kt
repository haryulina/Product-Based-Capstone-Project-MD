package com.user.phisheye.Data.Model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.user.phisheye.Data.API.ApiService
import com.user.phisheye.Data.Pref.Login
import com.user.phisheye.Data.Pref.Register
import com.user.phisheye.Data.Pref.Report
import com.user.phisheye.Tools.UserPreference
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class PhisingRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {
    fun register(name: String, email: String, password: String): MutableLiveData<Result<Register?>> {
        val responseLiveData: MutableLiveData<Result<Register?>> = MutableLiveData()
        responseLiveData.value = Result.Loading

        try {
            apiService.register(name, email, password).enqueue(object : Callback<Register>{
                override fun onResponse(call: Call<Register>, response: Response<Register>) {
                    if(response.isSuccessful){
                        responseLiveData.value  = Result.Success(response.body())
                    }else{
                        responseLiveData.value = Result.Error(response.message())
                        val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                        responseLiveData.value = Result.Error(errorMessage)
                    }
                }

                override fun onFailure(call: Call<Register>, t: Throwable) {
                    responseLiveData.value = Result.Error(t.message.toString())
                }

            })
        } catch (e: HttpException){

            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, Error::class.java)
            responseLiveData.value = errorResponse.message?.let { Result.Error(it) }
        }
        return  responseLiveData
    }
        fun login(email: String, password: String): MutableLiveData<Result<Login?>> {
        val responseLiveData: MutableLiveData<Result<Login?>> = MutableLiveData()
        responseLiveData.value = Result.Loading
        try {
            apiService.login(email, password).enqueue(object : Callback<Login> {
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    Log.wtf("RESPONSE", response.isSuccessful.toString())
                    if (response.isSuccessful) {
                        responseLiveData.value = Result.Success(response.body())
                    } else {
                        responseLiveData.value = Result.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    responseLiveData.value = Result.Error(t.message.toString())
                }
            })
        } catch (e: HttpException){

            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, Error::class.java)
            responseLiveData.value = errorResponse.message?.let { Result.Error(it) }
        }
        return responseLiveData
    }
    fun report(link: String): MutableLiveData<Result<Report?>> {
        val responseLiveData: MutableLiveData<Result<Report?>> = MutableLiveData()
        responseLiveData.value = Result.Loading
        try {
            apiService.report(link).enqueue(object : Callback<Report> {
                override fun onResponse(call: Call<Report>, response: Response<Report>) {
                    Log.wtf("RESPONSE", response.isSuccessful.toString())
                    if (response.isSuccessful) {
                        responseLiveData.value = Result.Success(response.body())
                    } else {
                        responseLiveData.value = Result.Error(response.message())
                    }
                }

                override fun onFailure(call: Call<Report>, t: Throwable) {
                    responseLiveData.value = Result.Error(t.message.toString())
                }
            })
        } catch (e: HttpException){

            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, Error::class.java)
            responseLiveData.value = errorResponse.message?.let { Result.Error(it) }
        }
        return responseLiveData
    }
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun clearLoginSession() {
        userPreference.logout()
    }
    companion object {
        @Volatile
        private var instance: PhisingRepository? = null
        fun getInstance(
            userPreference: UserPreference, apiService: ApiService
        ): PhisingRepository =
            instance ?: synchronized(this) {
                instance ?: PhisingRepository(userPreference, apiService)
            }.also { instance = it }
    }
}