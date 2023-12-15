package com.user.phisheye.View.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.phisheye.Data.PhisingRepository
import com.user.phisheye.Data.Pref.UserModel
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: PhisingRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}