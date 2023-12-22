package com.user.phisheye.View.Report

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.user.phisheye.Data.Model.UserModel
import com.user.phisheye.Data.Model.ViewModelFactory
import com.user.phisheye.R
import com.user.phisheye.View.Home.HomeActivity
import com.user.phisheye.View.Login.LoginActivity
import com.user.phisheye.View.Login.LoginViewModel
import com.user.phisheye.View.Register.RegisterActivity
import com.user.phisheye.databinding.ActivityLoginBinding
import com.user.phisheye.databinding.ActivityReportBinding
import com.user.phisheye.databinding.ActivityWelcomeBinding

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding
    private val viewModel by viewModels<ReportViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.reportBtn.setOnClickListener{ processReport()}

        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#890F0D")))
        actionBar?.title = Html.fromHtml("<font color='#EEEEEE'>Report New Scam</font>")

        //setupReport()
    }

    private fun processReport(){
        binding.apply {
            val link = reportEditText.text.toString()
            viewModel.report(link).observe(this@ReportActivity) { result ->
                Log.wtf("Result Report", result.toString())
                if (result != null) {
                    when (result) {
                        is com.user.phisheye.Data.Model.Result.Loading -> {
                            showLoading(true)
                            reportBtn.isEnabled = false
                        }
                        is com.user.phisheye.Data.Model.Result.Success -> {
                            showLoading(false)
                            reportBtn.isEnabled = true
                            viewModel.report(link)
                            showToast(getString(R.string.report_Succes))
                            moveToFinishReportActivity()
                        }
                        is com.user.phisheye.Data.Model.Result.Error -> {
                            showLoading(false)
                            reportBtn.isEnabled = true
                            showToast(getString(R.string.report_failed))
                        }

                        else -> {
                            // Handle other cases if necessary
                        }
                    }
                }
            }
        }
    }

//    private fun setupReport() {
//        binding.reportBtn.setOnClickListener {
//            startActivity(Intent(this, FinishReportActivity::class.java))
//        }
//    }
    private fun showLoading(isLoading: Boolean){
        binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun moveToFinishReportActivity(){
        val intent = Intent(this, FinishReportActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}