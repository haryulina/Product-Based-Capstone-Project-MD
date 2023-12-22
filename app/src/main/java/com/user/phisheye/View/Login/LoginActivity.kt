package com.user.phisheye.View.Login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.user.phisheye.Data.Model.PhisingRepository
import com.user.phisheye.Data.Model.UserModel
import com.user.phisheye.View.Home.HomeActivity
import com.user.phisheye.databinding.ActivityLoginBinding
import com.user.phisheye.Data.Model.ViewModelFactory
import com.user.phisheye.R
import com.user.phisheye.View.Register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{ processLogin()}

        binding.toRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        setupView()
        playAnimation()
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
        supportActionBar?.hide()
    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(100)
        val message =
            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(100)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)

        val dontreadyRegister = ObjectAnimator.ofFloat(binding.dontreadySignup, View.ALPHA, 1f).setDuration(100)
        val toRegister = ObjectAnimator.ofFloat(binding.toRegister, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                title,
                message,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login,
                dontreadyRegister,
                toRegister

            )
            startDelay = 100
        }.start()
    }
    private fun processLogin(){
        binding.apply {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            viewModel.login(email, password).observe(this@LoginActivity) { result ->
                Log.wtf("Result Login", result.toString())
                if (result != null) {
                    when (result) {
                        is com.user.phisheye.Data.Model.Result.Loading -> {
                            showLoading(true)
                            loginButton.isEnabled = false
                        }
                        is com.user.phisheye.Data.Model.Result.Success -> {
                            showLoading(false)
                            loginButton.isEnabled = true
                            viewModel.saveSession(UserModel(email, result.data?.loginResult?.token.orEmpty(), isLogin = true))
                            showToast(getString(R.string.login_succes))
                            moveToHomeActivity()
                        }
                        is com.user.phisheye.Data.Model.Result.Error -> {
                            showLoading(false)
                            loginButton.isEnabled = true
                            showToast(getString(R.string.login_failed))
                        }

                        else -> {
                            // Handle other cases if necessary
                        }
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean){
        binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun moveToHomeActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}


