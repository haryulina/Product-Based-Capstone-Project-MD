package com.user.phisheye.Tools

import android.content.Context
import com.user.phisheye.Data.API.ApiConfig
import com.user.phisheye.Data.Model.PhisingRepository

object Injection {
    fun providePhisingRepository(context: Context): PhisingRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.apiInstance
        return PhisingRepository.getInstance(pref, apiService)
    }
}