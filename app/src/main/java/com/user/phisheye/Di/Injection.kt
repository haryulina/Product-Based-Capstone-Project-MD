package com.user.phisheye.Di

import android.content.Context
import com.user.phisheye.Data.API.ApiConfig
import com.user.phisheye.Data.PhisingRepository
import com.user.phisheye.Data.Pref.UserPreference
import com.user.phisheye.Data.Pref.dataStore

object Injection {
    fun provideRepository(context: Context): PhisingRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return PhisingRepository.getInstance(pref, apiService)
    }
}