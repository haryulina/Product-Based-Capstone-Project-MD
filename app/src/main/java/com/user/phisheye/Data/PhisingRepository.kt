package com.user.phisheye.Data

import androidx.lifecycle.liveData
import com.user.phisheye.Data.API.ApiService
import com.user.phisheye.Data.Pref.UserModel
import com.user.phisheye.Data.Pref.UserPreference
import kotlinx.coroutines.flow.Flow
import java.io.File

class PhisingRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
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