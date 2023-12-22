package com.user.phisheye.View.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.user.phisheye.Data.API.ApiService
import com.user.phisheye.Data.API.PredictApiConfig
import com.user.phisheye.Data.Model.PhisingRepository
import com.user.phisheye.Data.Model.UserModel
import com.user.phisheye.Data.Pref.PredictPhishingResponse
import kotlinx.coroutines.launch

class HomeViewModel (private val phisingRepository: PhisingRepository) : ViewModel(){
    private val predictApiService: ApiService = PredictApiConfig.getPredictApiService()

    fun getSession(): LiveData<UserModel> {
        return phisingRepository.getSession().asLiveData()
    }

    fun Logout() {
        viewModelScope.launch {
            phisingRepository.clearLoginSession()
        }
    }

    fun predictPhishing(predictUrl: String, requestBody: Map<String, String>): LiveData<PredictPhishingResponse> {
        val resultLiveData = MutableLiveData<PredictPhishingResponse>()

        viewModelScope.launch {
            try {
                val response = predictApiService.predictPhishing(requestBody)
                resultLiveData.postValue(response.body())
            } catch (e: Exception) {
                resultLiveData.postValue(PredictPhishingResponse(prediction = "unknown_error"))
            }
        }

        return resultLiveData
    }
}