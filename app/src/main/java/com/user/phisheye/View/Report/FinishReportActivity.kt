package com.user.phisheye.View.Report

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.user.phisheye.R
import com.user.phisheye.View.Home.HomeActivity
import com.user.phisheye.databinding.ActivityFinishReportBinding

class FinishReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#890F0D")))
        actionBar?.title = Html.fromHtml("<font color='#EEEEEE'>Detection Result</font>")

        setupHomeBtn()
    }
    private fun setupHomeBtn() {
        binding.homeBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}