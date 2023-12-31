package com.user.phisheye.View.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.phisheye.Data.Model.PhisingRepository
import com.user.phisheye.Data.Model.Result
import com.user.phisheye.Data.Model.UserModel
import com.user.phisheye.Data.Pref.Login
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: PhisingRepository) : ViewModel() {

    fun login(email: String, password: String) = repository.login(email, password)

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
           repository.saveSession(user)
        }
    }


}