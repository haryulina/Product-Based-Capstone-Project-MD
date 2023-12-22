package com.user.phisheye.View.Home

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.user.phisheye.Data.Model.ViewModelFactory
import com.user.phisheye.R
import com.user.phisheye.View.Education.EducationActivity
import com.user.phisheye.View.Report.ReportActivity
import com.user.phisheye.View.Result.ResultNegativeActivity
import com.user.phisheye.View.Result.ResultPositiveActivity
import com.user.phisheye.View.welcome.WelcomeActivity
import com.user.phisheye.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private lateinit var detectEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#890F0D")))
        actionBar?.title = Html.fromHtml("<font color='#EEEEEE'>Home</font>")

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this)
        { user ->
            Log.wtf("user session", "User Token ${user.token}")
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }

        detectEditText = binding.detectEditText

        setupView()
        setupAction()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.set_menu -> {
                clearSession()
            }
        }

        return super.onOptionsItemSelected(item)
    }
    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun setupAction() {
        binding.moreTextView.setOnClickListener {
            val intent = Intent(this, EducationActivity::class.java)
            startActivity(intent)
        }

        binding.detectButton.setOnClickListener {
            val url = detectEditText.text.toString()

            if (url.isNotEmpty()) {
                val requestBody = mapOf("url" to url)

                viewModel.predictPhishing(url, requestBody).observe(this, { response ->
                    val prediction = response?.prediction
                    if (!prediction.isNullOrBlank()) {
                        showToast("Prediction: $prediction")

                        if (prediction.equals("Phishing", ignoreCase = true)) {
                            val positiveIntent = Intent(this, ResultPositiveActivity::class.java)
                            startActivity(positiveIntent)
                        }

                        else if (prediction.equals("Legitimate", ignoreCase = true)) {
                            val negativeIntent = Intent(this, ResultNegativeActivity::class.java)
                            startActivity(negativeIntent)
                        }
                    } else {
                        showToast("Failed to get prediction.")
                    }
                })
            } else {
                showToast("URL cannot be empty")
            }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clearSession() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.log_out)
            .setMessage(R.string.are_you_sure)
            .setPositiveButton(R.string.oke) { _, _ ->
                viewModel.Logout()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}