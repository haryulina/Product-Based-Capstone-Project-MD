package com.user.phisheye.View.Result

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.user.phisheye.R
import com.user.phisheye.View.Home.HomeActivity
import com.user.phisheye.databinding.ActivityReportBinding
import com.user.phisheye.databinding.ActivityResultNegativeBinding

class ResultNegativeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultNegativeBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#890F0D")))
        actionBar?.title = Html.fromHtml("<font color='#EEEEEE'>Detection Result</font>")

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