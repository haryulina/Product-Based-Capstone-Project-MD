package com.user.phisheye.View.Report

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.user.phisheye.R
import com.user.phisheye.View.Login.LoginActivity
import com.user.phisheye.databinding.ActivityReportBinding
import com.user.phisheye.databinding.ActivityWelcomeBinding

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#890F0D")))
        actionBar?.title = Html.fromHtml("<font color='#EEEEEE'>Report New Scam</font>")

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupReport()
    }

    private fun setupReport() {
        binding.reportBtn.setOnClickListener {
            startActivity(Intent(this, FinishReportActivity::class.java))
        }
    }
}