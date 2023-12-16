package com.user.phisheye.View.Report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.user.phisheye.R
import com.user.phisheye.View.Home.HomeActivity
import com.user.phisheye.databinding.ActivityFinishReportBinding

class FinishReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishReportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupHomeBtn()
    }

    private fun setupHomeBtn() {
        binding.homeBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}