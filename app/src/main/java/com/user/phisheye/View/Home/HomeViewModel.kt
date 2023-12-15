package com.user.phisheye.View.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.user.phisheye.Data.PhisingRepository
import com.user.phisheye.Data.Pref.UserModel
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: PhisingRepository) : ViewModel(){
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}