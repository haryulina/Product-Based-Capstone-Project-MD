package com.user.phisheye.View.Report

import androidx.lifecycle.ViewModel
import com.user.phisheye.Data.Model.PhisingRepository

class ReportViewModel (private val phisingRepository: PhisingRepository) : ViewModel() {
    fun report(link :String) = phisingRepository.report(link)

}