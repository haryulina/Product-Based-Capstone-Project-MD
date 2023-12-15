package com.user.phisheye.View.Register

import androidx.lifecycle.ViewModel
import com.user.phisheye.Data.Model.PhisingRepository

class RegisterViewModel (private val phisingRepository: PhisingRepository, ) : ViewModel() {
    fun register(name: String, email: String, password: String) = phisingRepository.register(name, email, password)
}